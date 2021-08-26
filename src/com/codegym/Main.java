package com.codegym;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    public static ProductManager productManager = new ProductManager();

    public static void main(String[] args) {
        int choice;

        do {
            menu();
            System.out.println("Nhập vào lựa chọn của bạn");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    writeDataToFile();
                    break;
                }
                case 2: {
                    readDataToFile();
                    break;
                }
                case 3: {
                    searchProduct();
                }
                case 0: {
                    System.out.println("Bye");
                    break;
                }
            }
        } while (choice != 0);
    }

    public static void searchProduct() {
        System.out.print("Nhập mã sản phẩm cần tìm: ");
        int productId = scanner.nextInt();
        int index = productManager.findProductById(productId);
        if(index != -1) {
            System.out.println("Sản phẩm bạn cần tìm là");
            System.out.println(productManager.getProduct().get(index));
        } else {
            System.out.println("Không tìm thấy");
        }}

    public static void writeDataToFile(){
        addProduct();
        ArrayList<Product> products = productManager.getProduct();
        writeToFile("product.txt", products);
    }

    public static void readDataToFile() {
        ArrayList<Product> productsDataFromFile = readDataFromFile("product.txt");
        for (Product products : productsDataFromFile){
            System.out.println(products);
        }
    }

    public static ArrayList<Product> readDataFromFile(String filePath) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            products = (ArrayList<Product>) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void writeToFile(String filePath, ArrayList<Product> products) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(products);
            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayProduct() {
        productManager.showProduct();
    }

    public static void addProduct() {
        Product product = inputProduct();
        productManager.addProduct(product);
    }

    public static Product inputProduct() {
        System.out.print("Nhập mã sản phẩm: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        System.out.print("Nhập hãng sản phẩm: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Nhập giá sản phẩm: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Nhập mô tả sản phẩm: ");
        String description = scanner.nextLine();
        return new Product(productId, name, manufacturer, price, description);
    }

    public static void menu() {
        System.out.println("---Menu---");
        System.out.println("1. Thêm sản phẩm mới vào file");
        System.out.println("2. Hiển thị sản phẩm có trong file");
        System.out.println("3. Tìm kiếm sản phẩm có trong file");
        System.out.println("0. Thoát");
    }
}
