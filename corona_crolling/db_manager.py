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

            sql = "INSERT INTO patient (patientid, sex, age, detail) VALUES ('%s', '%s', %s, '%s')" % (
                person_list[i]['patientid'],
                person_list[i]['sex'],
                person_list[i]['age'],
                person_list[i]['detail']
            )
            self.db.execute(sql)
            self.db.commit()

    def get_sex_num(self):
        sql = "SELECT SUM(*) FROM patient WHERE sex='남'"
        row = self.db.execute(sql)
