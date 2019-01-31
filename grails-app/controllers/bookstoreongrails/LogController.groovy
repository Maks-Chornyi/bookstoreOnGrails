package bookstoreongrails

class LogController {

    static allowedMethods = [deleteLogs: 'GET']

    LogService logService

    def logs() {
        [logsMap : logService.getAllLogs()]
    }

    def deleteLogs() {
        logService.deleteLogs()
        redirect(action: "logs")
    }
}
