import pymysql


class Database:
    def __init__(self):
        self.db = pymysql.connect(host='localhost',
                                  user='sercle',
                                  password='awdsd123',
                                  db='corona',
                                  charset='utf8')
        # self.db = pymysql.connect(host='localhost',
        #                           user='root',
        #                           password='awdsd123',
        #                           db='smilegate',
        #                           charset='utf8')
        self.db.autocommit(1)

    def execute(self, query, args={}):
        self.cursor = self.db.cursor(pymysql.cursors.DictCursor)
        self.cursor.execute(query, args)
        self.cursor.close()

    def executeOne(self, query, args={}):
        self.cursor = self.db.cursor(pymysql.cursors.DictCursor)
        self.cursor.execute(query, args)
        row = self.cursor.fetchone()
        self.cursor.close()
        return row

    def executeAll(self, query, args={}):
        self.cursor = self.db.cursor(pymysql.cursors.DictCursor)
        self.cursor.execute(query, args)
        row = self.cursor.fetchall()
        return row

    def commit(self):
        self.db.commit()
