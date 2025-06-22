a_list = ["apple", "banana", "carrot", "black", "box"]
b_list=[]
for item in a_list:
    if item.lower()[0]=="b":
        b_list.append(item.capitalize())
print(b_list)
