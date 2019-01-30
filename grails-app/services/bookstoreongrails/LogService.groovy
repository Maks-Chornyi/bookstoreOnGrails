package bookstoreongrails

import enums.Action
import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.plugins.redis.RedisService
import groovy.json.JsonSlurper
import redis.clients.jedis.Jedis

import static groovy.json.JsonParserType.LAX as RELAX

import java.sql.Timestamp
import java.time.Instant

@Transactional
class LogService {

    RedisService redisService
    Jedis jedis = new Jedis()

    Map<Date, String> getAllLogs() {
        Map<String, String> mapLogsKeyIsString = jedis.hgetAll("DeletedBook")
        Map<Date, String> mapLogsKeyIsData = new HashMap<>()
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
        String objJSON = obj as JSON
        redisService.hset(hashName,newLogKey, objJSON)
    }

    String createJsonForRedisLogs(Object obj, Action action) {
        String nameOfObject = obj.getClass()
        String objString = obj as JSON
        String jsonRes = """"""
            ${nameofObject} ${objString} {action: action.name()}

        """"""
    }

    void getValuesOfJSON(String json) {
        def jsonRes = new JsonSlurper().setType(RELAX).parseText(json)
        println jsonRes.getClass()
    }
}
