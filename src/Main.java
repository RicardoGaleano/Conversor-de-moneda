import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        bienvenida(teclado);
        menu();
        while (true){
            try {
                System.out.println("Ingresa la opcion que quieres realizar");
                int opcion= teclado.nextInt();
                teclado.nextLine();
                if (opcion==1){
                    menuTasasConversion();
                    String url="https://v6.exchangerate-api.com/v6/2c81e96e4852cd1f76ede71c/latest/USD";
                    while (true){

                        System.out.println("Ingresa la opcion o escribe 5 para salir ");
                        int opcion2= teclado.nextInt();
                        teclado.nextLine();
                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .build();
                        HttpResponse<String> response = client
                                .send(request, HttpResponse.BodyHandlers.ofString());
                        Gson gson=new Gson();
                        var cuerpo=response.body();
                        Exchange cambio=gson.fromJson(cuerpo,Exchange.class);
                        Map<String, Double> tasas=cambio.getConvesor();

                        try {
                            if (opcion2==1){
                                if (tasas.containsKey("EUR")){
                                    System.out.println("Ingresa la cantidad de EUR que quieres convertir a COP");
                                    double cantidad= teclado.nextInt();
                                    teclado.nextLine();
                                    double tasaEuro= tasas.get("COP");
                                    double resultado=tasaEuro*cantidad;
                                    System.out.println("***********************************");
                                    System.out.println("La cantidad de "+cantidad+ " EUR, equivale a "+resultado+ " COP");
                                    System.out.println("***********************************");
                                    menuTasasConversion();
                                }
                            }
                            else if (opcion2==2) {
                                if (tasas.containsKey("COP")){
                                    System.out.println("Ingresa la cantidad de COP que quieres convertir a EUR");
                                    double cantidad= teclado.nextInt();
                                    teclado.nextLine();
                                    double tasaCop= tasas.get("EUR");
                                    System.out.println(tasaCop);
                                    double resultado=tasaCop*cantidad;
                                    System.out.println("***********************************");
                                    System.out.println("La cantidad de "+cantidad+ " COP, equivale a "+resultado+ " EUR");
                                    System.out.println("***********************************");
                                    menuTasasConversion();
                                }
                            }
                            else if (opcion2==3) {
                                if (tasas.containsKey("USD")){
                                    System.out.println("Ingresa la cantidad de USD que quieres convertir a COP");
                                    double cantidad= teclado.nextInt();
                                    teclado.nextLine();
                                    double tasaCop= tasas.get("COP");
                                    double resultado=tasaCop*cantidad;
                                    System.out.println("***********************************");
                                    System.out.println("La cantidad de "+cantidad+ " USD, equivale a "+resultado+ " COP");
                                    System.out.println("***********************************");
                                    menuTasasConversion();
                                }
                            }
                            else if (opcion2==4) {
                                if (tasas.containsKey("EUR")){
                                    System.out.println("Ingresa la cantidad de COP que quieres convertir a COP");
                                    double cantidad= teclado.nextInt();
                                    teclado.nextLine();
                                    double tasaCop= tasas.get("USD");
                                    double resultado=tasaCop*cantidad;
                                    System.out.println("***********************************");
                                    System.out.println("La cantidad de "+cantidad+ " EUR, equivale a "+resultado+ " USD");
                                    System.out.println("***********************************");
                                    menuTasasConversion();
                                }
                            }
                            else if (opcion2==5) {
                                System.out.println("Saliendo del sistema");
                                break;
                            }
                        }catch (InputMismatchException e){
                            System.out.println("Ingresa una opcion valida de 1 a 5.");
                        }

                    }
                    break;
                } else if (opcion==2) {
                    System.out.println("Saliendo del programa");
                    break;
                }else {
                    System.out.println("Ingresa una opcion valida sea 1 o 2");
                }
            }catch (InputMismatchException e){
                System.out.println("Ingresa por favor una opcion valida, no caracteres diferentes a 1 o 2");
                teclado.nextLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static void menu() {
        System.out.println("Ingresa la opcion que quieres realizar");
        System.out.println("1. Convertir moneda");
        System.out.println("2. Salir del programa");
    }


    private static void bienvenida(Scanner teclado) {
        System.out.println("**************************************");
        System.out.println("Bienvenido al conversor de moneda");
        System.out.println("Ingresa tu nombre");
        String nombre=teclado.nextLine();
        System.out.println("Bienvenido "+nombre);
    }
    private static void menuTasasConversion() {
        System.out.println("Ingresa la opcion de conversion que desees");
        System.out.println("1. EUR a Cop");
        System.out.println("2. COP a EUR");
        System.out.println("3. USD a COP");
        System.out.println("4. EUR a USD");
        System.out.println("5. Salir");
    }
}
