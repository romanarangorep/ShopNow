package model;

public class Client {
    //siempre se escriben primero los atributos
    private String name;
    private String email;
    //el constructor es un metodo que siempre es publico que nos va a construir la clase
    public Client(String name, String email){
        //el this dice que le va a asignar un valor al atributo llamado
        this.name=name;
        this.email=email;
    }
    //luego van los demas metodos, pueden ser los getters y setters o mostrarcliente, etc.
    public void showInfo(){
    System.out.println("client information: "+"Client name: "+ name+" Client email: "+email);
    }


       

}
