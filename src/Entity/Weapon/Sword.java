package Entity.Weapon;

import java.awt.Image;

public class Sword extends Weapon {

    public Sword(int x, int y, Image weaponImage) {
        super(x, y, weaponImage);
        // Lakukan inisialisasi khusus untuk senjata pedang (jika ada)
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x; // Sesuaikan posisi X
        this.y = y; // Sesuaikan posisi Y
    }

    public void updatePosition(int handX, int handY) {
        // Mengatur posisi senjata relatif terhadap posisi tangan pemain
        this.x = handX - 5; // Sesuaikan posisi X relatif terhadap tangan pemain
        this.y = handY + 5; // Sesuaikan posisi Y relatif terhadap tangan pemain
    }

    // Implementasikan metode lain jika diperlukan untuk pedang khusus
}
