class Type:
    name = ""
    cate = 0
    max_pindex = 0

    def __init__(self, name, cate, max_pindex):
        self.name = name.lower().replace(" ", "_")
        self.cate = cate
        self.max_pindex = max_pindex

type_table = [
    Type("Read aloud", 22, 91),
    Type("Repeat Sentence", 23, 198),
    Type("Describe Image", 24, 80),
    Type("Retell Lecture", 25, 112),
    Type("Answer Short Question", 26, 173),
    Type("Summarize Written Text ", 28, 165),
    Type("Write Essay", 29, 109),
    Type("Re-Order", 31, 99),
    Type("Reading Fill In The Blanks ", 32, 61),
    Type("Summarize Spoken Text", 34, 93),
    Type("Write From Dictation", 35, 376),
    Type("Listening Fill In The Blanks ", 36, 99),
    Type("Highlight Incorrect Words", 37, 98),
]
