package Entity.Weapon;

import java.awt.Image;

public class Spear extends Weapon {
    // Konstruktor Spear yang memanggil konstruktor superclass Weapon
    public Spear(int x, int y, Image weaponImage) {
        super(x, y, weaponImage);
        // Tambahan pengaturan khusus untuk tombak
    }

    public void setPosition(int x, int y) {
        this.x = x; // Sesuaikan posisi X
        this.y = y; // Sesuaikan posisi Y
    }
    // Metode atau perilaku tambahan untuk tombak (jika ada)
}
