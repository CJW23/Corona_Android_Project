from bs4 import BeautifulSoup
from selenium import webdriver
from datetime import datetime
from db_manager import Manager

db_manager = Manager()
data_name = ['총확진자', '금일확진자', '검사중', '음성판']
person_data_name = ['patientid', 'sex', 'age', 'detail']
now_year = datetime.now().year      # 현재 년도
now_date = datetime.now()           # 현재 시간
new_insert_data = []                # 새롭게 삽입될 데이터
sum_data = []
person_data = []

# selenium을 사용하기 위한 크롬 웹드라이버 로컬 위치
driver = webdriver.Chrome('/Users/cjw/chromedriver/chromedriver')
# URL
driver.get('http://www.cheonan.go.kr/')
html = driver.page_source
soup = BeautifulSoup(html, 'html.parser')


rst = soup.find_all('dl', 'item')
total_rst = soup.find('ul', 'top_t')
total_data = total_rst.find_all('li')

for r in total_data:
    sum_data.append([r.find('span').get_text(), r.find('b').get_text()])
    #sum_data.append([r.find('span').get_text(), r.find('b').get_text()])

for rs in rst:
    info = rs.find('li').get_text().split(' ')
    detail = rs.find('p').get_text()
    number = info[0]
    sex = info[1]
    age = info[-1].replace("세)", '')
    try:
        #print(detail)
        person_data.append([number, sex, age, detail])
    except Exception as e:
        print("데이터 잘못")


person_list = []
for i in range(len(person_data)):
    dic = {}
    for j in range(4):
        dic[person_data_name[j]] = person_data[i][j]
    person_list.append(dic)

sum_list = {}
for i in range(len(sum_data)):
    sum_list[sum_data[i][0]] = sum_data[i][1]

#print(len(person_data))
#print(json.dumps(person_list, ensure_ascii=False, indent=4))
#print(json.dumps(sum_list, ensure_ascii=False, indent=4))
db_manager.delete_data()
db_manager.insert_total(sum_list)
db_manager.insert_patient(person_list)
