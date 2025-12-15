package org.bernhard.game;

import jakarta.persistence.*;

@Entity(
        name = "game_character"
)
@Table(
        name = "game_character",
        uniqueConstraints = @UniqueConstraint(
                name = "character_name_unique",
                columnNames = "character_name"
        )
)
public class GameCharacter {
    @Id
    @SequenceGenerator(
            name = "character_id_sequence",
            sequenceName = "character_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "character_id_sequence"
    )
    @Column(
            name = "character_id",
            nullable = false
    )
    private Long id;
    @Column(
            name = "character_name",
            nullable = false,
            unique = true
    )
    private String name;
    @Column(
            name = "character_class",
            nullable = false
    )
    private String characterClass;
    @Column(
            name = "character_health",
            nullable = false
    )
    private Integer health;
    @Column(
            name = "character_atk",
            nullable = false
    )
    private Integer attack;
    @Column(
            name = "character_def",
            nullable = false
    )
    private Integer defense;

    public GameCharacter(String name, String characterClass, Integer health, Integer attack, Integer defense) {
        this.name = name;
        this.characterClass = characterClass;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public GameCharacter() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "GameCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", characterClass='" + characterClass + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }
}
