class AppleBasket():
    def __init__(self,apple_color,apple_quantity=0):
        try:
            self.__apple_quantity = int(apple_quantity)
            self.__apple_color = str(apple_color)
            # value of apple quantity must be int and non negative
            if self.__apple_quantity < 0:
                raise ValueError
        except ValueError:
                raise ValueError(f"apple quantity '{apple_quantity}' isn't non-negative int")
    def increase(self):
        #increase the quantity of the apples in the basket by one
        self.__apple_quantity+=1


    def __str__(self):
        #override the str method to return a sentence that more readable
        return f'A basket of {str(self.__apple_quantity)} { self.__apple_color} apples.'

class GreenAppleBasket(AppleBasket):
    def __init__(self):
        super().__init__(apple_color="Green")


if __name__=='__main__':
    #giving exaples for constructing objects from different classes
    apple_basket1= AppleBasket("red",3)
    apple_basket2 = AppleBasket("blue", 49)
    apple_basket1.increase()
    apple_basket2.increase()
    print(apple_basket1)
    print(apple_basket2)


