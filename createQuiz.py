topic = input("Inserisci il nome dell'argomento (es. HeapSort): ")
out = open(topic+"Quiz.json","w")

def createQuestionObject():

    done = False

    questionsList = []



    while not done :
        questionAndAnswers = []
        domanda = input("Inserisci la domanda : ")
        questionAndAnswers.append(domanda)

        nrisp = int(input("Quante risposte vuoi inserire? -> "))
        questionAndAnswers.append(nrisp)

        correct = input("Inserisci la risposta corretta : ")
        questionAndAnswers.append(correct)

        for i in range(nrisp-1) :
            questionNumber = i + 2
            risposta = input("Inserisci la " + str(questionNumber) + "° risposta : ")
            questionAndAnswers.append(risposta)

        questionsList.append(questionAndAnswers)

        if input("Vuoi inserire un'altra domanda? (s/n)") != 's' :
            done = True
            return questionsList

def jsonify(obj) :
    jsoned = "{\n"

    jsoned += '\t"Topic" : "' + topic + '",\n'

    jsoned += '\t"Contenuto" :\n'
    jsoned += '\t\t\t\t[\n'

    for index,el in enumerate(obj) :
        jsoned += '\t\t\t\t\t' + '{\n'
        jsoned += '\t\t\t\t\t' + '"Domanda" : "' + el[0] + '" ,\n'
        jsoned += '\t\t\t\t\t' + '"nRisposte" : "' + str(el[1]) + '" ,\n'

        jsoned += '\t\t\t\t\t' + '"Risposte" :\n'
        jsoned += '\t\t\t\t\t\t\t[\n'

        for idx in range(el[1]) :
            tmp = el[2+idx]
            jsoned += '\t\t\t\t\t\t\t\t' + '"' + tmp + '"'
            if idx != el[1]-1 :
                jsoned += ','
            jsoned += '\n'

        jsoned += '\t\t\t\t\t\t\t' + ']\n'


        jsoned += '\t\t\t\t\t' + '}'
        if index != len(obj)-1 :
            jsoned += ','

        jsoned += '\n'


    jsoned += '\t\t\t\t]'


    jsoned += "\n}"
    return jsoned

qList = createQuestionObject()

toWrite = jsonify(qList)

out.write(toWrite)

out.close()
