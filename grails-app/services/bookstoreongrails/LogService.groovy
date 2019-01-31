package bookstoreongrails

import enums.Action
import grails.gorm.transactions.Transactional
import redis.clients.jedis.Jedis

import java.sql.Timestamp
import java.time.Instant

@Transactional
class LogService {

    def redisService

    Map<Date, String> getAllLogs() {

        Map<String, String> mapLogsKeyIsString = redisService.hgetAll("logs")
        Map<Date, String> mapLogsKeyIsData = new TreeMap<>()
        Set<String> keysOfStringMap = mapLogsKeyIsString.keySet()
        keysOfStringMap.each {key ->
            Long timeStampLong = Long.parseLong(key)
            Timestamp stamp = new Timestamp(timeStampLong)
            Date date = new Date(stamp.getTime())
            mapLogsKeyIsData.put(date, mapLogsKeyIsString.get(key))
        }
        mapLogsKeyIsData
    }

    void log(String hashName, Object obj, Action action) {
        long timeStampMls = Instant.now().toEpochMilli()
        String newLogKey = Long.toString(timeStampMls)
        redisService.hset(hashName, newLogKey, createLogStringForRedis(obj, action))
    }

    String createLogStringForRedis(Object obj, Action action) {
        String classNameOfObject = obj.class.getSimpleName()
        String nameOfObject
        if(obj instanceof Book) {
            nameOfObject = obj.title
        } else {
            nameOfObject = obj.name
        }
        classNameOfObject + " \"" + nameOfObject + "\" was " + action.name()
    }

    void deleteLogs() {
        redisService.del("logs")
    }
}
