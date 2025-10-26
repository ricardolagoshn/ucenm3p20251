package com.example.ucenm3p20251.configuraciones;

public class Transacciones
{
    // Nombre de la base de datos
    public static final String DBNAME = "PM01UCENM";

    //Nombre de la tabla de la base de datos
    public static final String TablePersonas = "personas";

    //Campos de la tabla personas
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String foto = "foto";

    // DDL
    public static final String CREATETABLEPERSONAS =
            "CREATE TABLE " + TablePersonas + " ( " +
                    nombres + " TEXT NOT NULL, " +
                    apellidos + " TEXT NOT NULL, " +
                    edad + " INTEGER, " +
                    correo + " TEXT UNIQUE, " +
                    foto + " TEXT ) " ;

    public static final String DROPTABLEPERSONAS = "DROP TABLE IF EXISTS " + TablePersonas;

    //DML - select , insert, update, delete
    public static final String SELECTTABLEPERSONAS = "SELECT * FROM " + TablePersonas;

}
