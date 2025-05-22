package br.com.fiap.fintech.model;

public class Conta {
    private int number;
    private int agency;
    private int bank;
    private String type;
    private double balance;

    public Conta() {
    }

    public Conta(int number, int agency, int bank, String type, double balance) {
        this.number = number;
        this.agency = agency;
        this.bank = bank;
        this.type = type;
        this.balance = balance;
    }

    public String displayAccount(){
        return "Dados da conta:\n número: " + this.number + "\n" + " agência: " + this.agency + "\n" + " banco: " + this.bank + "\n" + " tipo: " + this.type + "\n" + " saldo: " + this.balance;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAgency() {
        return agency;
    }

    public void setAgency(int agency) {
        this.agency = agency;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0  && this.balance >= amount) {
            this.balance -= amount;
            System.out.println("Saque de R$" + amount + " realizado com sucesso");
            System.out.println("Saldo disponivel na conta R$" + balance);
            System.out.println("---------------------------------------------------------");
            return true;
        }
        else{
            if (amount <= 0) {
                System.out.println("Digite um valor maior que zero");
            }
            else {
                System.out.println("Saldo insuficiente");
            }
            return false;
        }
    }
}