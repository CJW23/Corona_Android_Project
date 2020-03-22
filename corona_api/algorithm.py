str = input()
bom = input()

while True:
    #FRULA
    spl = str.split(bom)
    if len(spl) == 2 and spl[0] == '' and spl[1] == '':
        print("FRULA")
        break
    elif len(spl) == 1 and spl[0] == str:
        print(str)
        break
    else:
        str=""
        for i in range(len(spl)):
            str += spl[i]
