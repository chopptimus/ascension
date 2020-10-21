(ns ascension.core-test
  (:require [ascension.core :refer [macro-text]]
            [cljs.test :refer-macros [deftest is]]))

(def expected
  "/click CA2CharacterAdvancementMainClassButton1
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button3
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button3
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button4
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button5
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button3
/click DropDownList1Button3
/click CA2CharacterAdvancementMainClassButton2
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button3
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button3
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button4
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button5
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button3
/click DropDownList1Button3
/click CA2CharacterAdvancementMainClassButton3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button3
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button4
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button2
/click DropDownList1Button3
/click CA2CharacterAdvancementMainClassButton4
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button3
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button3
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button4
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button3
/click DropDownList1Button3
/click CA2CharacterAdvancementMainClassButton5
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button3
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button2
/click DropDownList1Button3
/click CA2CharacterAdvancementMainClassButton6
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button3
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button5
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button2
/click DropDownList1Button3
/click CA2CharacterAdvancementMainClassButton7
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button3
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button4
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button5
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button1
/click DropDownList1Button3
/click CA2CharacterAdvancementMainClassButton8
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button3
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button4
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button5
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button6
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button3
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button2
/click DropDownList1Button3
/click CA2CharacterAdvancementMainClassButton9
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button4
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button6
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree1.Content.Spells.Button7
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree2.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button1
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button2
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button3
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button4
/click DropDownList1Button3
/click CA2.CharacterAdvancementMain.Main.Tree3.Content.Spells.Button5
/click DropDownList1Button3
/click StaticPopup1Button1")

(deftest macro-text-test
  (let [picked #{{:name "Rend",
                  :image "Icons/Warrior/Ability_Gouge.png",
                  :class :warrior,
                  :spec :arms}
                 {:name "Battle Stance",
                  :image "Icons/Warrior/Ability_Warrior_OffensiveStance.png",
                  :class :warrior,
                  :spec :arms}
                 {:name "Charge",
                  :image "Icons/Warrior/Ability_Warrior_Charge.png",
                  :class :warrior,
                  :spec :arms}
                 {:name "Sprint",
                  :image "Icons/Rogue/Ability_Rogue_Sprint.png",
                  :class :rogue,
                  :spec :combat}}
        actual (macro-text picked)]
    (is (= expected actual))))
