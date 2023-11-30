package Entity.Weapon;

import java.awt.Image;

public class Bow extends Weapon {
    // Konstruktor Arrow yang memanggil konstruktor superclass Weapon
    public Bow(int x, int y, Image weaponImage) {
        super(x, y, weaponImage);
        // Tambahan pengaturan khusus untuk panah
    }

    public void setPosition(int x, int y) {
        this.x = x; // Sesuaikan posisi X
        this.y = y; // Sesuaikan posisi Y
    }
    // Metode atau perilaku tambahan untuk panah (jika ada)
}
