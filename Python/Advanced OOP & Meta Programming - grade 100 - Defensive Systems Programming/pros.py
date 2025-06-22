class User():
    def __init__(self,user_name, user_subject):
        self.__user_name=user_name
        self.__user_subject = user_subject
class Engineer(User):
    def __init__(self,user_name):
        super().__init__(user_name=user_name,user_subject="Engineer")
class Technician(User):
    def __init__(self,user_name):
        super().__init__(user_name=user_name,user_subject="Technician")
class Politician(User):
    def __init__(self,user_name):
        super().__init__(user_name=user_name,user_subject="Politician")
class ElectricalEngineer(Engineer):
    def __init__(self,user_name):
        super().__init__(user_name=user_name)
        self._User__user_subject = "Electrical Engineer"

class ComputerEngineer(Engineer):
    def __init__(self,user_name):
        super().__init__(user_name=user_name)
        self._User__user_subject="Computer Engineer"
class MachanicalEngineer(Engineer):
    def __init__(self,user_name):
        super().__init__(user_name=user_name)
        self._User__user_subject="Mechanical Engineer"

import random
import inspect
def method(attribute_name):#initialize a method for the class
    def new_method(self):
        print(f"The attribute {attribute_name} has the value {getattr(self, attribute_name)}")
    return  new_method
if __name__=='__main__':
    #giving exaples for constructing objects from different classes
    user=User("Guy","student")
    eng=Engineer("shlomtzion")
    tech=Technician("Omer")
    pol=Politician("kfir")
    ee=ElectricalEngineer("Yanai")
    ce=ComputerEngineer("Lior")
    me=MachanicalEngineer("Noa")
    while True:
        #making a list of all the current classes including those which created on runtime
        names_of_classes = [name for name, obj in globals().items() if inspect.isclass(obj)]
        class_name = input("Please enter the name of new class(-1 for exit): ")
        #checking if the name in the input of the class isnt already used
        while class_name in names_of_classes and class_name!="-1":
            class_name = input("Please enter the name of new class(-1 for exit): ")
        if class_name=="-1":
            break
        base_class=input("Please enter name of base class (blank if none): ")
        #checking if the name in the input of the base-class exist
        while base_class not in names_of_classes and base_class!="":
            base_class = input("Please enter name of base class (blank if none): ")
        #getting method name
        class_method = input(f"Please enter name of new method for class {class_name}:")
        #getting attribute name
        class_attribute=input(f"Please enter name of new attribute for class {class_name}:")
        #initialize attribute name to a random number
        attribute_value = random.randint(1, 100)
        if base_class == "":
            #base-class is empty therefore the base-class will be Object
            new_class = type(class_name, (), {class_attribute: attribute_value,class_method: method(attribute_name=class_attribute)})
        else:
            new_class = type(class_name, (globals().get(base_class),),{class_attribute: attribute_value, class_method: method(attribute_name=class_attribute)})
        #adding the new class that created to the globals and will be added also to the list after refreshing
        globals()[class_name] = new_class
        if new_class.__bases__[0].__name__!="object":
            #if the base class is object the print sentence will we empty
            print(f"Class {new_class.__name__} created with base class: {new_class.__bases__[0].__name__} ")
        else:
            print(f"Class {new_class.__name__} created with base class: ")
        #printing the a name of the new class
        print(f"Class __name__ is: {new_class.__name__} ")
        #printing the dictionary of the class
        print(f"Class __dict__ is: {new_class.__dict__} ")
