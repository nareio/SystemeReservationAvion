package org.example;

public class PersonnelCabine extends Employe{

    private String qualification;

    public PersonnelCabine(String nom, String adresse, String contact, String dateEmbauche,  String qualification) {
        super(nom, adresse, contact, dateEmbauche);
        this.qualification = qualification;
    }

    @Override
    public String obtenirRole() {
        return "Personnel de cabine";
    }

    @Override
    public void affecterVol(Vol vol) {
        vol.affecterEquipage(this);
    }

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() + "Role : " + obtenirRole() + ", qualification : " + this.qualification;
    }
}
