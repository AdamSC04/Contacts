import java.io.BufferedReader
import java.io.File
import java.io.PrintWriter

fun main(args: Array<String>) {
    val contacts: MutableList<Contact> = arrayListOf()

    while (true) {
        println("Meny:\n(1) Lägg till kontakt\n(2) Radera kontakt\n(3) Redigera kontakt\n(4) Visa kontaktlista\n(5) Spara kontaktlista\n(6) Importera kontaktlista")
        when (readln()) {
            "1" -> {
                println("Skriv in namn:")
                val name = readln()
                println("Skriv in efternamn:")
                val lastname = readln()
                println("Skriv in nummer:")
                val number = readln()
                println("Skriv in mail:")
                val email = readln()
                val person = Contact(name, lastname, number, email)
                contacts.add(person)
            }

            "2" -> {
                println("Skriv in förnman:")
                val name = readln()
                println("Skriv in efternamn:")
                val lastname = readln()
                val iterator = contacts.iterator()
                while (iterator.hasNext()) {
                    val next = iterator.next()
                    if (next.name == name && next.lastname == lastname) {
                        iterator.remove()
                    }
                }
            }

            "3" -> {
                println("Skriv in förnman:")
                val name = readln()
                println("Skriv in efternamn:")
                val lastname = readln()
                val iterator = contacts.iterator()
                while (iterator.hasNext()) {
                    val next = iterator.next()
                    if (next.name == name && next.lastname == lastname) {
                        println("Vad vill du ändra: \n(1) Förnamn\n(2) Efternamn\n(3) Nummer\n(4) Mail")
                        when (readln()) {
                            "1" -> {
                                println("Skriv in det nya namnet: ")
                                val newName = readln()
                                next.name = newName
                            }

                            "2" -> {
                                println("Skriv in det nya efternamnet: ")
                                val newName = readln()
                                next.lastname = newName
                            }

                            "3" -> {
                                println("Skriv in det nya numret: ")
                                val newNumber = readln()
                                next.number = newNumber
                            }

                            "4" -> {
                                println("Skriv in det nya mailet: ")
                                val newMail = readln()
                                next.email = newMail
                            }
                        }
                    }
                }
            }

            "4" -> {
                println("Visa: \n(1) Alfabetisk ordning \n(2) Kronologisk ordning")
                when (readln()) {
                    "1" -> {
                        contacts.sortBy { it.lastname } //Funkar inte
                        for (contact in contacts) {
                            contact.printDetails()
                        }
                    }
                    "2" -> {
                        println("Student Details:")
                        for (contact in contacts) {
                            contact.printDetails()
                        }
                    }
                }
            }

            "5" -> {
                File("contacts.txt").printWriter().use { out ->
                    contacts.forEach {
                        out.println(it.name)
                        out.println(it.lastname)
                        out.println(it.number)
                        out.println(it.email)
                    }
                }
            }

            "6" -> {
                contacts.clear()
                val bufferedReader: BufferedReader = File("contacts.txt").bufferedReader()
                val text: List<String> = bufferedReader.readLines()
                val details: MutableList<String> = arrayListOf()
                for ((counter, line) in text.withIndex()) {
                    details.add(line)
                    if (counter % 4 == 3) {
                        val person = Contact(details[0], details[1], details[2], details[3])
                        contacts.add(person)
                        details.clear()
                    }
                }
            }
        }
    }
}

class Contact(var name: String, var lastname: String, var number: String, var email: String) {
    fun printDetails() {
        println("$name $lastname, $number, $email")
    }
}