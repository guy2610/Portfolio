line = 'This line contains words and some have the letter o'
line_list=line.split(" ")
b=[item.upper() for item in line_list if "o" in item.lower()]
print(",".join(b))
