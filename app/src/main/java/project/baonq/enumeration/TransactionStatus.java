/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.baonq.enumeration;

/**
 *
 * @author ADMIN
 */
public enum TransactionStatus {
    DISABLE(0), ENABLE(1);
    private int status;

    TransactionStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
