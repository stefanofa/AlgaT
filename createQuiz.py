import json
from numpy.random import permutation

topic = input("Inserisci il nome dell'argomento (es. HeapSort): ")
out = open(topic+"Quiz.json","w")

nQuestions = 0

def createQuestionObject():
    global nQuestions
    done = False

    questionsList = []

    nrisp = 4

    while not done :
        questionAndAnswers = []
        domanda = input("Inserisci la domanda : ")
        questionAndAnswers.append(domanda)

        questionAndAnswers.append(nrisp)

        if input("Vuoi inserire un'immagine relativa alla domanda? (s/n)") == 's' :
            imgName = input("Inserisci il nome dell'immagine, comprensivo di estensione : ")
        else :
            imgName = "null"

        questionAndAnswers.append(imgName)

        correct = input("Inserisci la risposta corretta : ")
        questionAndAnswers.append(correct)

        for i in range(nrisp-1) :
            questionNumber = i + 2
            risposta = input("Inserisci la " + str(questionNumber) + "° risposta : ")
            questionAndAnswers.append(risposta)

        questionsList.append(questionAndAnswers)
        nQuestions+=1

        if input("Vuoi inserire un'altra domanda? (s/n)") != 's' :
            done = True
            return questionsList


def jsonify(obj) :
    jsoned = "{\n"

    jsoned += '\t"Topic" : "' + topic + '",\n'

    jsoned += '\t' + '"nDomande" : ' + str(nQuestions) + ' ,\n'

    jsoned += '\t"Domande" :\n'
    jsoned += '\t\t\t\t[\n'


    for index,el in enumerate(obj) :
        jsoned += '\t\t\t\t\t' + '{\n'
        jsoned += '\t\t\t\t\t' + '"Domanda" : "' + el[0] + '" ,\n'
        jsoned += '\t\t\t\t\t' + '"nRisposte" : ' + str(el[1]) + ' ,\n'
        jsoned += '\t\t\t\t\t' + '"imgName" : "' + el[2] + '" ,\n'

        jsoned += '\t\t\t\t\t' + '"Risposte" :\n'
        jsoned += '\t\t\t\t\t\t\t[\n'

        nIter = 1
        indexCorrectAnswer = 0
        for idx in permutation(el[1]) :
            if idx == 0 :
                indexCorrectAnswer = nIter
            tmp = el[3+idx]
            jsoned += '\t\t\t\t\t\t\t\t' + '"' + tmp + '"'
            if nIter != 4 :
                jsoned += ','
            jsoned += '\n'
            nIter += 1

        jsoned += '\t\t\t\t\t\t\t' + '] ,\n'
        jsoned += '"RispostaCorretta" : ' + str(indexCorrectAnswer) +'\n'

        jsoned += '\t\t\t\t\t' + '}'
        if index != len(obj)-1 :
            jsoned += ','

        jsoned += '\n'


    jsoned += '\t\t\t\t]'


    jsoned += "\n}"

    print("jsoned = " + jsoned)

    d = json.loads(jsoned)
    jsoned = json.dumps(d, indent=4)

    return jsoned

def main():
    qList = createQuestionObject()

    print(qList)

    toWrite = jsonify(qList)

    out.write(toWrite)

    out.close()

if __name__== "__main__":
    main()
