package com.example.alkrox.usefulseconds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlkRox on 19/07/2017.
 */

public class AssociationDatabase extends SQLiteOpenHelper{
    public static final String ASSOC_KEY_ID = "id";
    public static final String ASSOC_NAME = "name";
    public static final String ASSOC_CATEGORY = "category";
    public static final String ASSOC_MONEY = "money";
    public static final int DATABASE_VERSION = 2;

    public static final String ASSOC_TABLE_NAME = "association";
    public static final String ASSOC_TABLE_CREATE =
            "CREATE TABLE " + ASSOC_TABLE_NAME + "(" +
                    ASSOC_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ASSOC_NAME + " TEXT, " +
                    ASSOC_CATEGORY + " TEXT, " +
                    ASSOC_MONEY + " INTEGER" +
                    ");";


    public AssociationDatabase(Context context) {
        super(context, ASSOC_TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ASSOC_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On supprime l'ancienne table si elle existe
        db.execSQL("DROP TABLE IF EXISTS " + ASSOC_TABLE_NAME);
        // Et on recrée une autre table
        onCreate(db);
    }

    // Ajouter une assoc
    public void addAssociation(Association association) {
        //permet d'accéder à un objet SQLiteDatabase en écriture.
        SQLiteDatabase db = this.getWritableDatabase();

        //L'objet ContentValues permet de définir des clés/valeurs. La clé représente l'identifiant de la colonne
        // de la table et la valeur représente le contenu de l'enregistrement dans cette colonne.
        // ContentValues peut être utilisé pour insertions et mises à jour des enregistrements de la base de données.
        ContentValues values = new ContentValues();
        values.put(ASSOC_NAME, association.getName());
        values.put(ASSOC_CATEGORY, association.getCatetory());
        values.put(ASSOC_MONEY, association.getMoney());

        // On met les valeurs dans la table
        db.insert(ASSOC_TABLE_NAME, null, values);
        db.close();
    }

    // Recuperer une assoc dans la base par son ID
    public Association getAssociation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        //Une requête retourne un objet Cursor
        //Un curseur représente le résultat d'une requête
        Cursor cursor = db.query(ASSOC_TABLE_NAME, new String[]{ASSOC_KEY_ID,
                        ASSOC_NAME, ASSOC_CATEGORY, ASSOC_MONEY}, ASSOC_KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        //Les null sont pour les options de la fonction query, avec les orderby groupby...
        if (cursor != null)
            cursor.moveToFirst();

        //On crée une nouvelle assoc qui va avoir les informations de l'assoc trouvée dans la base de données
        Association associationFromBdd = new Association(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                Integer.parseInt(cursor.getString(3))
        );
        // On renvoie l'assoc
        return associationFromBdd;
    }

    //Recuperer la liste de toutes les assoc par categorie
    public List<Association> getAssocInCategory(String category)
    {
        List<Association> associationList = new ArrayList<Association>();

        SQLiteDatabase db = this.getWritableDatabase();

        //On fait la requete avec le nom mis en parametre
        Cursor cursor = db.query(ASSOC_TABLE_NAME, new String[]{ASSOC_KEY_ID,
                        ASSOC_NAME, ASSOC_CATEGORY, ASSOC_MONEY }, ASSOC_CATEGORY + "=?",
                new String[]{String.valueOf(category)}, null, null, null, null);

        //Pour chaque assoc trouvée, on crée une assoc et on lui affecte ses valeurs
        //On les ajoute ensuite à la liste
        if (cursor.moveToFirst()) {
            do {
                Association associationFromBdd = new Association(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3))
                );
                associationList.add(associationFromBdd);
            } while (cursor.moveToNext());
        }
        // On renvoie la liste
        return associationList;
    }

    public int updateAssociation(Association association) {
        SQLiteDatabase db = this.getWritableDatabase();

        //L'objet ContentValues permet de définir des clés/valeurs. La clé représente l'identifiant de la colonne
        // de la table et la valeur représente le contenu de l'enregistrement dans cette colonne.
        // ContentValues peut être utilisé pour insertions et mises à jour des enregistrements de la base de données.
        ContentValues values = new ContentValues();
        values.put(ASSOC_KEY_ID, association.getId());
        values.put(ASSOC_NAME, association.getName());
        values.put(ASSOC_CATEGORY, association.getCatetory());
        values.put(ASSOC_MONEY, association.getMoney());

        // On met a jour la table
        return db.update(ASSOC_TABLE_NAME, values, ASSOC_KEY_ID + " = ?",
                new String[]{String.valueOf(association.getId())});
    }

    // Supprimer une assoc
    public void deleteAssociation(Association association) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ASSOC_TABLE_NAME, ASSOC_KEY_ID + " = ?",
                new String[]{String.valueOf(association.getId())});
        db.close();
    }
}
