package org.example;

public class PersonnelCabine extends Employe{

    private String qualification;

    public PersonnelCabine(String nom, String adresse, Personne contact, int numeroEmploye, String dateEmbauche,  String qualification) {
        super(nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.qualification = qualification;
    }

    @Override
    public String obtenirRole() {
        return "Personnel de cabine";
    }

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() + "Role : " + obtenirRole() + ", qualification : " + this.qualification;
    }
}
