package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1 - Cadastrar produto.");
            System.out.println("2 - Buscar produto por código.");
            System.out.println("3 - Sair.");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarProduto(productManager, scanner);
                        break;
                    case 2:
                        buscarProduto(productManager, scanner);
                        break;
                    case 3:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida! Por favor, digite um número.");
                scanner.nextLine();
                opcao = 0;
            }
        } while (opcao != 3);

        scanner.close();
    }

    private static void cadastrarProduto(ProductManager productManager, Scanner scanner) {
        System.out.println("CADASTRO DE PRODUTO ===");

        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o preço do produto: R$ ");
        double preco = scanner.nextDouble();

        Product produto = new Product(codigo, nome, preco);
        boolean cadastrado = productManager.addProduct(produto);

        if (cadastrado) {
            System.out.println("\nProduto cadastrado com sucesso!");
        } else {
            System.out.println("\nERRO: Já existe um produto com este código!");
        }
    }

    private static void buscarProduto(ProductManager productManager, Scanner scanner) {
        System.out.println("\n=== BUSCA DE PRODUTO ===");
        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();

        Product produto = productManager.findProductByCode(codigo);

        if (produto != null) {
            System.out.println("\nProduto encontrado:");
            System.out.println(produto);
        } else {
            System.out.println("\nProduto não encontrado!");
        }
    }
}

class Product {
    private int code;
    private String name;
    private double price;

    public Product(int code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Código: " + code +
                "\nNome: " + name +
                "\nPreço: R$ " + String.format("%.2f", price);
    }
}

class ProductManager {
    private Map<Integer, Product> products;

    public ProductManager() {
        this.products = new HashMap<>();
    }

    public boolean addProduct(Product product) {
        if (products.containsKey(product.getCode())) {
            return false;
        }

        products.put(product.getCode(), product);
        return true;
    }

    public Product findProductByCode(int code) {
        return products.get(code);
    }
}