/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import utils.MyConnection;

/**
 *
 * @author Iheb
 */
public class DatetimetrTableRestoService {

    Connection cnx;
    
    public DatetimetrTableRestoService() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
}
