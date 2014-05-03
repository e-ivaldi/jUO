package com.foolver.juo.packetHandling.packets.utils;


public enum Skill {
  
  ALCHEMY((short) 0),
  ANATOMY((short) 1),
  ANIMALLORE((short) 2),
  ITEM_IDENTIFICATION((short) 3),
  ARMS_LORE((short) 4),
  PARRYING((short) 5),
  BEGGING((short) 6),
  BLACKSMITHING((short) 7),
  BOWCRAFT_FLETCHING((short) 8),
  PEACEMAKING((short) 9),
  CAMPING((short) 10),
  CARPENTRY((short) 11),
  CARTOGRAPHY((short) 12),
  COOKING((short) 13),
  DETECTING_HIDDEN((short) 14),
  ENTICEMENT((short) 15),
  EVALUATE_INTELLIGENCE((short) 16),
  HEALING((short) 17),
  FISHING((short) 18),
  FORENSIC_EVALUATION((short) 19),
  HERDING((short) 20),
  HIDING((short) 21),
  PROVOCATION((short) 22),
  INSCRIPTION((short) 23),
  LOCKPICKING((short) 24),
  MAGERY((short) 25),
  RESISTING_SPELLS((short) 26),
  TACTICS((short) 27),
  SNOOPING((short) 28),
  MUSICIANSHIP((short) 29),
  POISONING((short) 30),
  ARCHERY((short) 31),
  SPIRIT_SPEAK((short) 32),
  STEALING((short) 33),
  TAILORING((short) 34),
  ANIMAL_TAMING((short) 35),
  TASTE_IDENTIFICATION((short) 36),
  TINKERING((short) 37),
  TRACKING((short) 38),
  VETERINARY((short) 39),
  SWORDSMANSHIP((short) 40),
  MACE_FIGHTING((short) 41),
  FENCING((short) 42),
  WRESTLING((short) 43),
  LUMBERJACKING((short) 44),
  MINING((short) 45),
  MEDITATION((short) 46),
  STEALTH((short) 47),
  REMOVE_TRAP((short) 48),
  NECROMANCY((short) 49),
  FOCUS((short) 50),
  CHIVALRY((short) 51);
       
  short value;

  Skill(Short value) {
    this.value = value;
  }

  public short getValue() {
    return this.value;
  }

  public static Skill fromValue(short s) {
      
    for (Skill d : values()) {
      if (d.getValue() == s) {
        return d;
      }
    }
    throw new IllegalArgumentException(String.format("unable to find the skill with id %s", s));
  }
}
