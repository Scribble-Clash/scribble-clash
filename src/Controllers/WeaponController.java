package Controllers;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import Entity.Hand;
import Entity.Player;
import Entity.Weapon.Weapon;

public class WeaponController implements MouseMotionListener {
    private Weapon weapon;
    private Player player;
    private Hand hand;
    private List<Weapon> weapons;

    public WeaponController(Weapon weapon, Player player) {
        this.weapon = weapon;
        this.player = player;
    }

    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        // Mendapatkan posisi mouse dalam koordinat game (relative terhadap frame/window
        // game)
        Point playerPos = player.getHandPosition();
        int weaponX = playerPos.x;
        int weaponY = playerPos.y;

        // Hitung vektor dari senjata ke posisi mouse
        int dx = mouseX - weaponX;
        int dy = mouseY - weaponY;

        // Hitung sudut dari vektor
        double angle = Math.atan2(dy, dx);

        // Ubah sudut menjadi derajat dan sesuaikan orientasi senjata
        int rotationAngle = (int) Math.toDegrees(angle);
        weapon.setOrientation(rotationAngle);
    }

    public void moveOtherWeapons(int rotationAngle) {
        for (Weapon weapon : weapons) {
            // Periksa apakah senjata bukan tangan
            if (weapon != hand.getHeldWeapon()) {
                weapon.setOrientation(rotationAngle);
            }
        }
    }

    // Metode lain dari interface MouseMotionListener
    public void mouseDragged(MouseEvent e) {
    }
}
