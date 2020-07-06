/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controller;

import com.bitlab.abstracts.AbstractManagement;
import com.bitlab.dao.UserDao;
import com.bitlab.entities.Rol;
import com.bitlab.entities.User;
import com.bitlab.util.Sha;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author nativi
 */
public class UserManagement extends AbstractManagement<User> {

    private static Logger logger = LoggerFactory.getLogger(UserDao.class);

    UserDao uDao = new UserDao();

    @Override
    public void captureData(User u, String user) {
        try {
            logger.debug("Captura de datos a guardar");
            System.out.print("Escriba el usuario nuevo y despues presione [enter] o [Cancel] para cancelar");
            u.setUser(getCapture(user));
            System.out.print("Escriba la contraseña y despues presione [enter] o [Cancel] para cancelar");
            u.setPassword(getCapture(user));
            System.out.print("Escriba el ID de rol 1 es administrador y 2. rrhh y despues presione [enter] o [Cancel] para cancelar");
            Rol r = new Rol(validatedNumber(user));
            u.setRolNo(r);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addRecord(String user) {
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        logger.debug("Creacion de Nuevo Rol de Usuario");
        User u = new User(0);
        u.setUserChange(user);
        u.setDateChange(new Date());
        u.setUserCreate(user);
        u.setDateCreate(new Date());
        logger.debug("Se ejecuta el metodo captuteData");
        captureData(u, user);
        try {
            uDao.create(u);
        } catch (SQLException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la creacion", ex);
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la creacion", ex);
        }
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    @Override
    protected String getFindToString(int id) {
        logger.debug("Obtiene los datos segun id de registro");
        try {
            return "--" + find(id).getUserNo() + " " + find(id).getUser() + " [contraseña] " + find(id).getRolNo().getRolRol() + "--";

        } catch (Exception e) {
            return "No se encontraron resultados";
        }
    }

    @Override
    public void updateRecord(String user) {
        try {
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            logger.debug("Actualización de registro de usuario");
            System.out.println("Ingrese el codigo de registro y posteriormente presione [enter] o [Cancel] para cancelar");

            User u = new User(validatedNumber(user));
            System.out.println("Estos son los registros actuales, actualice los registros que desee y escriba nuevamente los que no desea actualizar o [Cancel] para cancelar:");
            System.out.println(getFindToString(u.getUserNo()));
            u.setUserChange(user);
            u.setDateChange(new Date());
            logger.debug("Ejectuta metodo captuteData");
            captureData(u, user);
            try {
                uDao.update(u);
            } catch (SQLException | ClassNotFoundException ex) {
                logger.error("Ha ocurrido una excepcion en la actualizacion", ex);

            }
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void remove(int id) {
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        logger.debug("Eliminación de registro usuario");
        try {
            uDao.remove(id);
        } catch (SQLException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la actualizacion", ex);
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la actualizacion", ex);
        }
    }

    @Override
    protected List<User> getFindAll() {
        try {
            logger.debug("Se obtiene toda la lista de usuarios");
            return uDao.findAll();
        } catch (SQLException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la eliminación", ex);
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la eliminación", ex);
        }
        return null;
    }

    @Override
    protected User find(int id) {
        try {
            logger.debug("Se obtiene los datos del registro de usuario");
            return uDao.find(id);
        } catch (SQLException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la eliminación", ex);
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Ha ocurrido una excepcion en la eliminación", ex);
        }
        return null;
    }

    @Override
    protected List<User> findLike(String user) {
        System.out.println("Ingrese el usuario para buscar similitudes o presiones [cancel] para cancelar");
        try {
            return uDao.findLike(getCapture(user));
        } catch (SQLException | ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void login() {
        RrhhManagement rh;
        AdminManagement am;
        User u = null;
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Ingrese su usuario");
            String user = scan.nextLine();
            System.out.println("Ingrese su contraseña");
            String pass = Sha.encrypt(scan.nextLine());
            u = uDao.login(user, pass);
            if (u == null) {
                System.out.println("Credenciales invalidas intente nuevamente\n");
                flag = true;
            } else {
                if (u.getRolNo().getRolRolNo() == 1) {
                    System.out.println("-----Bienvenido " + user + " al menu de recursos humanos-----");
                    am = new AdminManagement();
                    am.adminMenu(user);

                } else {
                    rh = new RrhhManagement();
                    System.out.println("-----Bienvenido " + user + " al menu de recursos humanos-----");
                    rh.menuManagement(user);
                }
                boolean flag2 = true;
                while (flag2) {
                    System.out.println("Desea iniciar sesion con otra cuenta\n A.Si\nB.No");
                    if (scan.nextLine().toLowerCase().equals("a")) {
                        flag = true;
                        flag2 = false;
                    } else {
                        if (scan.nextLine().toLowerCase().equals("b")) {
                            System.out.println("Finalizando");
                            flag = false;
                            flag2 = false;
                        } else {

                            System.out.println("Ingrese una de las opciones validas");
                            flag2 = true;
                        }

                    }
                }
            }

        }
    }

}
