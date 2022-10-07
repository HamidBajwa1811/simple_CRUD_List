package com.example.crudtask

class ContactModel1 {
    var img = 0
    var name: String
    var number: String

    constructor(img: Int, name: String, number: String) {
        this.name = name
        this.number = number
        this.img = img
    }

    //    new code
    constructor(name: String, number: String) {
        this.name = name
        this.number = number
    }
}