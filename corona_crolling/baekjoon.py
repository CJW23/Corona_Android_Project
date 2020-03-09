num = input()

for i in range(int(num)):
    rst = ""
    a, b = input().split()
    for z in range(len(b)):
        for j in range(int(a)):
            rst += b[z]
    print(rst)
