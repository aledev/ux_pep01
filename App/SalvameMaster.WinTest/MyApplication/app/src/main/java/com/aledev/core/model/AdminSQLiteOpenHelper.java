package com.aledev.core.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alejandro on 19-05-2017.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates the "usuario" table
        db.execSQL("create table usuario(id integer primary key, idPersona integer, " +
                "fchcreate text, idEstado integer)");

        // Creates the "persona" table
        db.execSQL("create table persona(id integer primary key, nombre text, apellidoPaterno text, " +
                "apellidoMaterno text, nombreCompleto text, email text, fchNacimiento text, " +
                "fchRegistro text, sexo text, idTipoPersona integer, idEstado integer, foto blob)");

        // Creates the "trabajador" table
        db.execSQL("create table trabajador(id integer primary key, idPersona integer, idTipoTrabajador integer, " +
                "fchInicioTrabajador text, precioHora real, precioVisita real, telefono text, direccion text, " +
                "idRegion integer, latitud text, longitud text, descripcion text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {
        // Drops the "usuario" table and re-creates it
        db.execSQL("drop table if exists usuario");
        db.execSQL("create table usuario(id integer primary key, idPersona integer, " +
                "fchcreate text, idEstado integer)");

        // Drops the "persona" table and re-creates it
        db.execSQL("drop table if exists persona");
        db.execSQL("create table persona(id integer primary key, nombre text, apellidoPaterno text, " +
                "apellidoMaterno text, nombreCompleto text, email text, fchNacimiento text, " +
                "fchRegistro text, sexo text, idTipoPersona integer, idEstado integer, foto blob)");

        // Drops the "trabajador" table and re-creates it
        db.execSQL("drop table if exists trabajador");
        db.execSQL("create table trabajador(id integer primary key, idPersona integer, idTipoTrabajador integer, " +
                "fchInicioTrabajador text, precioHora real, precioVisita real, telefono text, direccion text, " +
                "idRegion integer, latitud text, longitud text, descripcion text)");
    }
}
