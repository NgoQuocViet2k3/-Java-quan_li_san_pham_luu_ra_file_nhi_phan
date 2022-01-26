package com.codegym;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        int choice = -1;
        ProductManagement productManagement = new ProductManagement();
        productManagement.products = readFile("product.txt");
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn");
            choice = input.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Danh sách sản phẩm:");
                    if (productManagement.size() == 0) {
                        System.out.println("Không có sản phẩm nào");
                    } else {
                        productManagement.display();
                    }
                    break;
                }
                case 2: {
                    System.out.println("---Thêm mới sản phẩm---");
                    System.out.println("Nhập vị tí muốn thêm sản phẩm");
                    input.nextLine();
                    Product product = inputProduct();
                    productManagement.add(product);
                    writeToFile("product.txt", productManagement.products);
                }
                case 3: {
                    System.out.println("Nhập mã sản phẩm cần tìm:");
                    String id = input.nextLine();
                    int index = productManagement.indexFindById(id);
                    if (index == -1) {
                        System.out.println("Mã sản phẩm không hợp lệ ");
                    } else {
                        System.out.println("Sản phẩm cần tìm là " + productManagement.products.get(index));
                    }
                    break;
                }
            }
        } while (choice != -1);

    }

    private static Product inputProduct() {
        System.out.println("Nhập mã sản phẩm");
        String id = scanner.nextLine();
        System.out.println("Nhập tên sản phẩm");
        String name = scanner.nextLine();
        System.out.println("Nhập hãng sản xuất");
        String manufacturer = scanner.nextLine();
        System.out.println("Nhập giá ");
        double price = scanner.nextDouble();
        System.out.println("Nhập mô tả");
        String description = scanner.nextLine();
        return new Product(id, name, manufacturer, price, description);
    }

    private static void menu() {
        System.out.println("---Menu---");
        System.out.println("1. Hiển thị tất cả sản phẩm");
        System.out.println("2. Thêm một sản phẩm mới vào trong danh sách");
        System.out.println("3. Tìm kiếm sản phẩm bằng mã sản phẩm");
    }

    public static void writeToFile(String path, List<Product> products) {
        try {
            OutputStream os = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Product> readFile(String path) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            InputStream is = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(is);
            products = (ArrayList<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }


}
