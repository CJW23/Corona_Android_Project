from flask import Flask, jsonify
from flask_restful import reqparse, abort, Api, Resource
from db_manager import Manager

app = Flask(__name__)
api = Api(app)
manager = Manager()
app.config['JSON_AS_ASCII'] = False


# 환자 성별 현황
class PatientSexSum(Resource):
    def get(self):
        print(manager.get_sex_num())
        return jsonify(manager.get_sex_num())


class Patient(Resource):
    def get(self):
        return jsonify(manager.get_detail())


class Total(Resource):
    def get(self):
        return jsonify(manager.get_total())


api.add_resource(PatientSexSum, '/sum/')
api.add_resource(Patient, '/patient/')
api.add_resource(Total, '/total/')
if __name__ == '__main__':
    app.run(host="0.0.0.0", port="8081", debug=True)