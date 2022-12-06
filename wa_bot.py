from flask import Flask, request, Response
import requests
from datetime import datetime
from twilio.twiml.messaging_response import MessagingResponse

app = Flask(__name__)

option = ''
confirmation = False
id_number = ''
verification = False
user_id = ''

@app.route('/bot', methods=['post'])
def bot():
    user_msg = request.values.get('Body', '').lower()
    bot_resp = MessagingResponse()
    msg = bot_resp.message()

    global option
    global confirmation
    global id_number
    global verification
    global user_id

    if user_msg and option == '' and confirmation == False and id_number == '':
        msg.body("¡Hola 😀, bienvenido a tu asistente virtual Sura!\n \nEscribe tu número de cédula para confirmar que eres nuestro usuario. 🧑")
        option = 'cedula'

    elif user_msg and option == 'cedula' and id_number == '':
        msg.body(f'Tu cédula es {user_msg}, ¿Correcto?')
        option = 'confirmation'
        id_number = user_msg
    
    elif user_msg and option == 'confirmation' and id_number != '':
        if 'si' in user_msg:
            user_data = request_user(id_number)
            if user_data == "sin datos":
                msg.body('Usted no está registrado en nuestro sistema. Comuníquese al número +11111111111 para adquirir una nueva póliza.')
                option = ''
                id_number = ''
                confirmation = False
            else:
                msg.body(f'Usted es nuestro cliente/a: {user_data["nombres"]} \n\nEscriba la palabra -consultar- si desea conocer su información de póliza. \nEscriba -asesor- si desea comunicarse con un asesor. \nEscriba -pqr- si desea registrar una PQR en nuestro sistema.')
                option = 'options'
                confirmation = True
                user_id = user_data['id']
        elif 'no' in user_msg:
            msg.body("Digite su cedula de nuevo.")
            option = 'cedula'
            id_number = ''
            user_id = ''
            print(option)
        else:
            msg.body('Datos incorrectos. Volviendo al inicio.')
            option = ''
            confirmation = False
            id_number = ''
            user_id = ''
    elif user_msg and option == 'options' and confirmation == True:
        if 'consultar' in user_msg and id_number != '':
            data = request_user(id_number)
            msg.body(f'Esta es su información completa: \nNombre: {data["nombres"]} \nNúmero de identificación: {data["numeroIdentificacion"]} \nNúmero de póliza: {data["numeroPoliza"]} \nFecha de vencimiento de la póliza: {data["fechaVigenciaPoliza"]} \nNúmero de Placa: {data["placaVehiculoAsegurado"]} \n\nDigite la palabra -si- para continuar.')
            option = 'confirmation'
        elif 'asesor' in user_msg and id_number != '':
            msg.body('Haga click o toque en la pantalla sobre el número +11111111111 para comunicarse con un asesor.')
            confirmation = False
            id_number = ''
            option = ''
            user_id = ''
        elif 'pqr' in user_msg and id_number != '':
            msg.body('Escriba su mensaje de PQR. Su queja será registrada y atendida lo más pronto posible.')
            option = 'pqr'
        else:
            msg.body('Datos incorrectos. Volviendo al inicio. Que tenga un buen día.')
            confirmation = False
            id_number = ''
            option = ''
            user_id = ''
    elif user_msg and option == 'pqr' and confirmation == True:
        response = post_pqr(id_number, user_msg, user_id)
        if (response == '<Response [200]>'):
            msg.body(f'Su PQR, realizada el {datetime.now()}, ha sido registrada con éxito. \nEsta es la información: \nCédula: {id_number} \nId de usuario: {user_id} \n Mensaje: {user_msg} \n\n Regresando al principio. Que tenga un buen día.')
            confirmation = False
            id_number = ''
            option = ''
            user_id = ''
        elif (response != '<Response [200]>'):
            msg.body('Ha ocurrido un error. Inténtelo más tarde. \n\n Volviendo al principio.')
            confirmation = False
            id_number = ''
            option = ''
            user_id = ''
   
    return Response(str(bot_resp), mimetype="application/xml")


def request_user(id_number):
    response = requests.get(f'http://localhost:8092/chatbot-svc/findByDocument?document={id_number}')
    try:
        data = response.json()[0]
    except:
        data = "sin datos"
    return data

def post_pqr(documento, comentario, id_usuario):
    data_post = { 
        'documento': f'{documento}',
        'comentariosPQRS': f'{comentario}',
        'idUsuario': f'{id_usuario}'
    }
        
    r = requests.post(url="http://localhost:8092/chatbot-svc/newPqrs", json=data_post)

    return str(r)

if __name__ == '__main__':
    app.run(debug=True)