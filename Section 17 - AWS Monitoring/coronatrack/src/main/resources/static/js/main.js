var myVar = 10;

setTimeout(console.log(myVar), 2000);

function createPerson(firstName, lastName) {
    var _firstName = firstName;
    var _lastName = lastName;
    return {
        setFirstName: firstName => _firstName = firstName,
        getFirstName: () => _firstName,
        setLastName: lastName => _lastName = lastName,
        getLastName: () => _lastName
    };
}

var a = () => 2;
a();
var person = createPerson("Art", "Shyshkin");
console.log(person.getFirstName());
console.log(person.getLastName());
person.setFirstName("Kate");
person.setLastName("Dobryden");
console.log(person.getFirstName());
console.log(person.getLastName());


for (var i = 0; i < 10; i++) (i => setTimeout(() => console.log(i), 1000))(i);


var stud1 = createStudent("Art", "Shyshkin", "male");
console.log(stud1);
var stud2 = new Student("Kate", "Shyshkina", "female");
console.log(stud2);
var stud3 = new createStudent("Art1", "Shyshkin1", "male");
console.log(stud3);
var stud4 = Student("Kate1", "Shyshkina1", "female");
console.log(stud4);


function createStudent(firstName, lastName, gender) {

    var obj = {};
    obj.firstName = firstName;
    obj.lastName = lastName;
    obj.gender = gender;
    return obj;
}

function Student(firstName, lastName, gender) {
    //var this = {}; - think of constructor mode that JS add this at start
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    //return this; - think of constructor mode that JS add this at end
}

function Bicycle(cadence, speed, gear, tirePressure) {
    this.cadence = cadence;
    this.speed = speed;
    this.gear = gear;
    this.tirePressure = tirePressure;
    this.inflateTires = function () {
        this.tirePressure += 3;
    }
}

var bicycle1 = new Bicycle(50,20,4,25);
console.log(bicycle1);
bicycle1.inflateTires();
console.log(bicycle1);

function Mechanic(name) {
    this.name=name;
}

var mike = new Mechanic("Mike");
mike.inflateTires = bicycle1.inflateTires;
mike.inflateTires.call(bicycle1);
console.log(bicycle1);
console.log(mike);

