import random
import string
import sys
import os

names = [
    "Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hannah", "Isaac", "Julia",
    "Kevin", "Laura", "Michael", "Nora", "Oliver", "Penny", "Quincy", "Rachel", "Samuel", "Tina",
    "Ulysses", "Victoria", "William", "Xander", "Yasmine", "Zachary",
    "Ava", "Benjamin", "Catherine", "Daniel", "Emily", "Felix", "Gabrielle", "Henry", "Isabel", "Jacob",
    "Katherine", "Liam", "Mia", "Nathan", "Olivia", "Peter", "Quinn", "Rebecca", "Samuel", "Tara",
    "Uma", "Vincent", "Wendy", "Xavier", "Yvonne", "Zane",
    "Amelia", "Brandon", "Chloe", "Dylan", "Ella", "Frederick", "Grace", "Harrison", "Ivy", "James",
    "Kylie", "Lucas", "Madison", "Natalie", "Oscar", "Penelope", "Quinn", "Riley", "Sophia", "Tristan",
    "Ursula", "Violet", "William", "Xena", "Yara", "Zoe"
]

lastnames = [
    "Anderson", "Baker", "Clark", "Davis", "Evans", "Fisher", "Garcia", "Harris", "Ingram", "Johnson",
    "Kaplan", "Lopez", "Martinez", "Nelson", "Owens", "Perez", "Quinn", "Ramirez", "Smith", "Taylor",
    "Ullman", "Valdez", "Williams", "Xiong", "Young", "Zhang",
    "Adams", "Brown", "Carter", "Diaz", "Edwards", "Flores", "Gomez", "Hernandez", "Irwin", "Jackson",
    "Kennedy", "Lee", "Mitchell", "Nguyen", "Ortiz", "Patel", "Rodriguez", "Scott", "Thomas", "Upton",
    "Vargas", "White", "Xu", "Yates", "Zimmerman",
    "Bell", "Cooper", "Dixon", "Ellis", "Ferguson", "Gordon", "Harrison", "Isaacs", "Jones", "Keller",
    "Lewis", "Miller", "Nixon", "OConnor", "Parker", "Quinn", "Roberts", "Sullivan", "Turner", "Upton",
    "Vasquez", "Walker", "Xu", "Yoder", "Zimmerman",
    "Allen", "Butler", "Cruz", "Davies", "Elliott", "Ford", "Gonzalez", "Hall", "Iverson", "Jenkins",
    "Khan", "Lopez", "Morgan", "Nguyen", "Owens", "Perez", "Quinn", "Reyes", "Stewart", "Tran",
    "Ullman", "Vargas", "Watson", "Xu", "Young", "Zimmerman"
]

services = {
    "Bar": ("Conexión a Internet de alta velocidad en todas las áreas del hotel para que puedas estar siempre conectado." , "Bar"),
    "Gimnasio": ("Disfruta de un desayuno variado con opciones calientes y frías, perfecto para comenzar tu día." , "Gimnasio"),
    "Internet": ("Amplio estacionamiento seguro para los huéspedes que llegan en automóvil." , "Internet"),
    "Lavanderia": ("Una piscina espaciosa con áreas de descanso para relajarte bajo el sol." , "Lavanderia"),
    "Piscina": ("Equipo de ejercicio moderno y un gimnasio bien equipado para mantenerse en forma durante tu estadía." , "Piscina"),
    "Prestamo": ("Solicita comida, bebidas y servicios a tu habitación en cualquier momento del día o la noche." , "Prestamo"),
    "Restaurante": ("Lavado y planchado de ropa disponible para los huéspedes." , "Restaurante"),
    "SPA": ("Relájate y rejuvenece con tratamientos de spa y masajes en el lugar." , "SPA"),
    "SalonConferencia": ("Saborea deliciosas comidas en el restaurante del hotel con opciones gastronómicas variadas." , "SalonConferencia"),
    "SalonReunion": ("Un lugar ideal para disfrutar de bebidas y socializar con otros huéspedes." , "SalonReunion"),
    "Supermercado": ("Atención al cliente disponible en todo momento para ayudarte con tus necesidades." , "Supermercado"),
    "Tienda": ("Consejos y asistencia personalizada para planificar actividades y excursiones locales." , "Tienda")
    
}

# services = [
#     "Wi-Fi gratuito", "Estacionamiento", "Desayuno buffet", "Piscina al aire libre", "Gimnasio",
#     "Servicio de habitaciones", "Recepción 24 horas", "Restaurante en el lugar", "Servicio de lavandería",
#     "Servicio de transporte al aeropuerto"
# ]

# consumptions = [
#     "Almuerzo en el restaurante del hotel", "Cena en el restaurante del hotel", "Desayuno en la habitación",
#     "Minibar", "Llamadas telefónicas internacionales", "Servicio de lavandería", "Servicio de tintorería",
#     "Internet en la habitación", "Servicio de habitaciones", "Estacionamiento", "Alquiler de coche", "Excursiones", "Spa y masajes",
#     "Servicio de niñera", "Consumo de la tienda de regalos", "Alquiler de bicicletas", "Uso del gimnasio",
#     "Cobro por películas en la habitación", "Servicio de despertador", "Llamadas locales", "Servicio de impresión y fax",
#     "Tarifa de resort", "Impuestos y tasas", "Botellas de agua en la habitación", "Servicio de transporte al aeropuerto",
#     "Conferencias y reuniones", "Impresión de documentos", "Compras en el minibar", "Room service de madrugada",
#     "Consumo del bar del hotel", "Salida tardía", "Cargo por llave perdida", "Uso del centro de negocios",
#     "Uso del centro de convenciones", "Uso de la piscina cubierta", "Caja fuerte en la habitación", "Artículos de tocador de lujo",
#     "Servicio de manicura y pedicura", "Alquiler de equipos deportivos", "Periódico en la habitación", "Consumo de alimentos para mascotas",
#     "Servicio de transporte local", "Uso de la sala de juegos", "Cambio de moneda", "Alquiler de equipos de esquí",
#     "Paseos en barco", "Servicio de catering para eventos", "Depósito por objetos de valor",
# ]



def username(name, lastname):
    username = name[:3] + lastname[:3] + str(random.randint(1, 99999))
    return username

def password(length):
    characters = string.ascii_letters + string.digits + string.punctuation
    password = ''.join(random.choice(characters) for _ in range(length))
    return password.replace("'", "").replace("`", "").replace(",", "").replace(":", "")

def document():
    document = ''.join(str(random.randint(0, 9)) for _ in range(10))
    return document

def generateDataServicios():
    with open("Servicios.csv", "w") as file:
        sys.stdout = file
        for i in range(len(services)):
            servicio = list(services.items())[i]
            data = servicio[0] + ","
            data = data + servicio[1][0]
            data = data + ","
            data = data + str(random.randint(10000, 100000))
            data = data + ","
            data = data + random.choice([ "manana", "noche", "tarde",])
            data = data + ","
            data = data + servicio[1][1]
            data = data + ","
            data = data + str(random.randint(3, 15))
            data = data + ","
            data = data + str(i + 1)
            print(data)

    with open("Servicios.csv", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateSQLServicios():
    with open("Servicios.sql", "w") as file:
        sys.stdout = file
        for i in range(len(services)):
            servicio = list(services.items())[i]
            data = "INSERT INTO SERVICIOS (NOMBRE, DESCRIPCION, COSTOPORUNIDAD, HORARIO, TIPOSERVICIO, CAPACIDAD, ID) VALUES (" + "'" + servicio[0] + "'" + ","
            data = data + "'" + servicio[1][0] + "'"
            data = data + ","
            data = data + str(random.randint(10000, 100000))
            data = data + ","
            data = data + "'" + random.choice([ "manana", "noche", "tarde",]) + "'"
            data = data + ","
            data = data + "'" + servicio[1][1] + "'"
            data = data + ","
            data = data + str(random.randint(3, 15))
            data = data + ","
            data = data + "servicios_sequence.nextval"
            data = data + ");"
            print(data)

def generateDataUsuarios(num):
    with open("Usuarios.csv", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = str(i + 1) + ","
            name = random.choice(names)
            lastname = random.choice(lastnames) 
            data = data + username(name, lastname)
            data = data + ","
            data = data + password(random.randint(8, 16))
            data = data + ","
            data = data + name + " " + lastname
            data = data + ","
            data = data + document()
            data = data + ","
            data = data + name.lower() + lastname[0:random.randint(0, len(lastname))].lower() + (str(random.randint(0, 9999)) if random.choice([True, True, False]) else "") + "@" + random.choice(["gmail", "yahoo", "hotmail", "outlook"]) + ".com"
            data = data + ","
            data = data + random.choice(["CC"])
            data = data + ","
            data = data + str(random.randint(1, 5)) # TIPODEUSUARIO_ID
            print(data)

    with open("Usuarios.csv", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateSQLUsuarios(num):
    with open("Usuarios.sql", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = "INSERT INTO USUARIOS (ID, USUARIO, CONTRASENA, NOMBRE, DOCUMENTO, EMAIL, TIPODOCUMENTO, TIPOSUSUARIO_ID) VALUES (" + "usuarios_sequence.nextval" + ","
            name = random.choice(names)
            lastname = random.choice(lastnames)
            data = data + "'" + username(name, lastname) + "'"
            data = data + ","
            data = data + "'" + password(random.randint(8, 16)) + "'"
            data = data + ","
            data = data + "'" + name + " " + lastname + "'"
            data = data + ","
            data = data + document()
            data = data + ","
            data = data + "'" + name.lower() + lastname[0:random.randint(0, len(lastname))].lower() + (str(random.randint(0, 9999)) if random.choice([True, True, False]) else "") + "@" + random.choice(["gmail", "yahoo", "hotmail", "outlook"]) + ".com" + "'"
            data = data + ","
            data = data + "'" + random.choice(["CC"]) + "'"
            data = data + ","
            data = data + str(random.randint(1, 5)) # TIPODEUSUARIO_ID
            data = data + ");"
            print(data)

    with open("Usuarios.sql", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateDataHabitaciones(pisos, habitacionesxpiso):
    with open("Habitaciones.csv", "w") as file:
        sys.stdout = file
        for i in range(1, pisos + 1):
            for j in range(1, habitacionesxpiso + 1):
                hab = (i * habitacionesxpiso) + j 
                data = str(hab) + ","
                data = data + str(i) + (str(0) if j < 10 else "") + str(j)
                data = data + ","
                data = data + str(i)
                data = data + ","
                data = data + "0"
                data = data + ","
                data = data + str(random.randint(1, 3)) # TIPOSHABITACION_ID
                print(data)
    
    with open("Habitaciones.csv", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateSQLHabitaciones(pisos, habitacionesxpiso):
    with open("Habitaciones.sql", "w") as file:
        sys.stdout = file
        for i in range(1, pisos + 1):
            for j in range(1, habitacionesxpiso + 1):
                data = "INSERT INTO HABITACIONES (ID, NUMERO, PISO, OCUPADA ,TIPOSHABITACION_ID) VALUES (" + "habitaciones_sequence.nextval" + ","
                data = data + str(i) + (str(0) if j < 10 else "") + str(j)
                data = data + ","
                data = data + str(i)
                data = data + ","
                data = data + "0"
                data = data + ","
                data = data + str(random.randint(1, 3)) # TIPOSHABITACION_ID
                data = data + ");"
                print(data)
    
    with open("Habitaciones.sql", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateDataReservasHabitaciones(num, rooms, users):
    with open("ReservasHabitaciones.csv", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = str(i + 1) + ","
            data = data + str(random.randint(1, 6))
            data = data + ","
            data = data + str(random.randint(1, 28)) + "/" + str(random.randint(1, 6)) + "/" + str(random.randint(2020, 2021))
            data = data + ","
            data = data + str(random.randint(1, 28)) + "/" + str(random.randint(6, 12)) + "/" + str(random.randint(2021, 2022))
            data = data + ","
            data = data + str(random.randint(1, rooms))
            data = data + ","
            data = data + str(random.randint(1, users))
            data = data + ","
            data = data + str(random.randint(1, 2)) # PLANESDECONSUMO_ID
            print(data)

    with open("ReservasHabitaciones.csv", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateSQLReservasHabitaciones(num, rooms, users):
    with open("ReservasHabitaciones.sql", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = "INSERT INTO RESERVASALOJAMIENTO (ID, NUMPERSONAS, FECHAIN, FECHAOUT, HABITACIONES_ID, USUARIOS_ID, PLANESCONSUMO_ID) VALUES (" + "reservasalojamiento_sequence.nextval" + ","
            data = data + str(random.randint(1, 6))
            data = data + ","
            data = data + "TO_DATE('" + str(random.randint(1, 28)) + "/" + str(random.randint(1, 6)) + "/" + str(random.randint(2020, 2021)) + "', 'DD/MM/YYYY')"
            data = data + ","
            data = data + "TO_DATE('" + str(random.randint(1, 28)) + "/" + str(random.randint(6, 12)) + "/" + str(random.randint(2021, 2022)) + "', 'DD/MM/YYYY')"
            data = data + ","
            data = data + str(random.randint(1, rooms))
            data = data + ","
            data = data + str(random.randint(1, users))
            data = data + ","
            data = data + str(random.randint(1, 2)) # PLANESDECONSUMO_ID
            data = data + ");"
            print(data)

    with open("ReservasHabitaciones.sql", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateDataReservasServicios(num, rooms):
    with open("ReservasServicios.csv", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = str(i + 1) + ","
            data = data +  str(random.randint(1, 28)) + "/" + str(random.randint(1, 6)) + "/" + str(random.randint(2020, 2021)) 
            data = data + ","
            data = data +  str(random.randint(1, 28)) + "/" + str(random.randint(6, 12)) + "/" + str(random.randint(2021, 2022)) 
            data = data + ","
            data = data  + str(random.randint(1, 28)) + "/" + str(random.randint(6, 12)) + "/" + str(random.randint(2021, 2022))
            data = data + ","
            data = data + str(random.randint(1, rooms))
            data = data + ","
            data = data + str(random.randint(1, 10)) # SERVICIOS_ID
            print(data)

    with open("ReservasServicios.csv", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateSQLReservasServicios(num, rooms):
    with open("ReservasServicios.sql", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = "INSERT INTO RESERVASSERVICIO (ID, FECHA, HORAINICIO, HORAFIN ,HABITACIONES_ID, SERVICIOS_ID) VALUES (" + "reservasservicio_sequence.nextval" + ","
            data = data + "TO_DATE('" + str(random.randint(1, 28)) + "/" + str(random.randint(1, 6)) + "/" + str(random.randint(2020, 2021)) + "', 'DD/MM/YYYY')"
            data = data + ","
            data = data + "TO_DATE('" + str(random.randint(1, 28)) + "/" + str(random.randint(6, 12)) + "/" + str(random.randint(2021, 2022)) + "', 'DD/MM/YYYY')"
            data = data + ","
            data = data + "TO_DATE('" + str(random.randint(1, 28)) + "/" + str(random.randint(6, 12)) + "/" + str(random.randint(2021, 2022)) + "', 'DD/MM/YYYY')"
            data = data + ","
            data = data + str(random.randint(1, rooms))
            data = data + ","
            data = data + str(random.randint(1, 10)) # SERVICIOS_ID
            data = data + ");"
            print(data)

    with open("ReservasServicios.sql", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateDataConsumos(num, rooms, users):
    with open("Consumos.csv", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = str(i + 1) + ","
            data = data + str(random.randint(100000, 1000000))
            data = data + ","
            data = data + str(random.randint(1, 28)) + "/" + str(random.randint(1, 6)) + "/" + str(random.randint(2020, 2021))
            data = data + ","
            data = data + str(random.randint(100000, 1000000))
            data = data + ","
            data = data + str(random.randint(1, rooms))
            data = data + ","
            data = data + str(random.randint(1, 10))
            data = data + ","
            data = data + "1"
            print(data)

    with open("Consumos.csv", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

def generateSQLConsumos(num, rooms, users):
    with open("Consumos.sql", "w") as file:
        sys.stdout = file
        for i in range(num):
            data = "INSERT INTO CONSUMOS (ID, CANTIDAD, FECHA, TOTAL, HABITACIONES_ID, SERVICIOS_ID, PRODUCTOS_ID) VALUES (" + "consumos_sequence.nextval" + ","
            data = data + str(random.randint(100000, 1000000))
            data = data + ","
            data = data + "TO_DATE('" + str(random.randint(1, 28)) + "/" + str(random.randint(1, 6)) + "/" + str(random.randint(2020, 2021)) + "', 'DD/MM/YYYY')"
            data = data + ","
            data = data + str(random.randint(100000, 1000000))
            data = data + ","
            data = data + str(random.randint(1, rooms))
            data = data + ","
            data = data + str(random.randint(1, 10))
            data = data + ","
            data = data + "1"
            data = data + ");"
            print(data)

    with open("Consumos.sql", "rb+") as file:
        file.seek(-1, os.SEEK_END)
        file.truncate()

users = int(input("Number of users: "))
floors = int(input("Number of floors: "))
roomsPerFloor = int(input("Number of rooms per floor: "))
roomReservations = int(input("Number of room reservations: "))
serviceReservations = int(input("Number of service reservations: "))
consumption = int(input("Number of consumptions: "))

generateDataServicios()
generateSQLServicios()
generateDataUsuarios(users)
generateSQLUsuarios(users)
generateDataHabitaciones(floors, roomsPerFloor)
generateSQLHabitaciones(floors, roomsPerFloor)
generateDataReservasHabitaciones(roomReservations, floors * roomsPerFloor, users)
generateSQLReservasHabitaciones(roomReservations, floors * roomsPerFloor, users)
generateDataReservasServicios(serviceReservations, users)
generateSQLReservasServicios(serviceReservations, users)
generateDataConsumos(consumption, roomReservations, users)
generateSQLConsumos(consumption, roomReservations, users)