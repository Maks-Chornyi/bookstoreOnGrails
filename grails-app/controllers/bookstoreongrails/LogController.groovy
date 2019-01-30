package bookstoreongrails

class LogController {

    LogService logService

    def logs() {
        String json = '''
            {
                "id": 500,
                "message": "hello"
            }
        '''
        logService.getValuesOfJSON(json)
        [logsMap : logService.getAllLogs()]
    }
}
