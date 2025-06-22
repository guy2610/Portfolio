import ast
import os
import types

def is_valid_python_code(code_string):
    #checking if the input code is legit python code
    try:
        ast.parse(code_string)
        return True
    except SyntaxError:
        return False

def load_module_from_file(file_name):
    #loading the module of a given file name
    module_name = os.path.splitext(os.path.basename(file_name))[0]
    module = types.ModuleType(module_name)
    with open(file_name, "r", encoding="utf-8") as file:
        code = file.read()
        exec(code, module.__dict__)
    return module

def change_method_code(method_code, py_code):
    #changing the code of the method in the classes
    m_code = method_code.split("\n")
    m_code.insert(1, "        " + py_code)
    return "\n".join(m_code)

def extract_main_code(file_content):
    # get the string of the main function from the input file
    tree = ast.parse(file_content)
    for node in ast.walk(tree):
        if isinstance(node, ast.If):
            # Updated for Python 3.14+
            if (isinstance(node.test, ast.Compare) and
                isinstance(node.test.left, ast.Name) and
                node.test.left.id == "__name__" and
                isinstance(node.test.comparators[0], ast.Constant) and
                node.test.comparators[0].value == "__main__"):
                return ast.get_source_segment(file_content, node)
    return ""


while True:
    try:
        name_file = input("Enter python file name (or -1 to exit): ")
        if name_file == '-1':
            break

        with open(name_file, "r", encoding='utf-8') as file:
            file_content = file.read()

            # Prompt for Python code to insert
            py_code = input("Enter a Python code to insert into methods: ")
            while not is_valid_python_code(py_code):
                print("The string is not valid Python code.")
                py_code = input("Enter a valid Python code: ")

            # Load the module from the file
            module = load_module_from_file(name_file)

            # Extract classes and their methods
            classes = []
            for node in ast.walk(ast.parse(file_content)):
                if isinstance(node, ast.ClassDef):
                    class_info = {
                        "class_name": node.name,
                        "methods": []
                    }
                    for class_node in node.body:
                        if isinstance(class_node, ast.FunctionDef):
                            method_code = ast.get_source_segment(file_content, class_node)
                            class_info["methods"].append({
                                "method_name": class_node.name,
                                "method_code": method_code
                            })
                    classes.append(class_info)

            # Extract the main code
            main_code = extract_main_code(file_content)

            # Construct the modified code with new methods
            modified_code = ""
            for cls in classes:
                modified_code += f"\nclass {cls['class_name']}:\n"
                for method in cls['methods']:
                    modified_method_code = change_method_code(method['method_code'], py_code)
                    modified_code += f"    {modified_method_code}\n"

            # Execute the modified code
            exec(modified_code + main_code, globals())

    except FileNotFoundError:
        print(f"File: {name_file} not found, please try again")