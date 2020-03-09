from db_module import Database


class Manager:
    def __init__(self):
        self.db = Database()

    def delete_data(self):
        sql = "DELETE FROM patient"
        self.db.execute(sql)
        sql = "DELETE FROM total"
        self.db.execute(sql)
        self.db.commit()

    def insert_total(self, sum_list):
        data = []
        for key, val in sum_list.items():
            data.append(val)
        # total_sum, today, checking, positive
        sql = "INSERT INTO total (total_sum, today, checking, positive) VALUES (%s, %s, %s, %s)" % (
            data[0],
            data[1],
            data[2],
            data[3]
        )
        self.db.execute(sql)
        self.db.commit()

    def insert_patient(self, person_list):
        print(str(len(person_list)))
        for i in range(len(person_list)):

            sql = "INSERT INTO patient (sex, age, detail) VALUES ('%s', %s, '%s')" % (
                person_list[i]['sex'],
                person_list[i]['age'],
                person_list[i]['detail']
            )
            self.db.execute(sql)
            self.db.commit()

    def get_sex_num(self):
        dic = {}
        sql = "SELECT sex, SUM(1) as sum FROM patient GROUP BY sex"
        rows = self.db.executeAll(sql)
        #for row in rows:
        #    dic[row['sex']] = int(row['sum'])
        return rows

    def get_detail(self):
        data = []
        sql = "SELECT patientid, sex, age, detail FROM patient"
        rows = self.db.executeAll(sql)
        for row in rows:
            dic = {}
            dic['patientid'] = row['patientid']
            dic['sex'] = row['sex']
            dic['age'] = row['age']
            dic['detail'] = row['detail']
            data.append(dic)
        return data

    def get_total(self):
        data = {}
        sql = "SELECT * FROM total"
        row = self.db.executeOne(sql)
        data['total_sum'] = row['total_sum']
        data['today'] = row['today']
        data['checking'] = row['checking']
        data['positive'] = row['positive']

        rows = self.get_sex_num()
        for row in rows:
            data[row['sex']] = int(row['sum'])
            
        print(data)
        return data