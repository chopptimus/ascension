(ns ^:figwheel-hooks ascension.core
  (:require [goog.dom :as gdom]
            [goog.string :as gstring]
            goog.string.format
            [re-com.core :as rc :refer [box h-box v-box gap input-textarea]]
            [reagent.core :as reagent :refer [atom]]
            [reagent.dom :as rdom]))

(defonce app-state (atom {:picked #{}}))

(def all-spells
  {:druid
   {:balance
    [{:name "Thorns"
      :image "Icons/Druid/Spell_Nature_Thorns.png"}
     {:name "Wrath"
      :image "Icons/Druid/Spell_Nature_AbolishMagic.png"}
     {:name "Moonfire"
      :image "Icons/Druid/Spell_Nature_StarFall.png"}]
    :feral
    [{:name "Demoralizing Roar"
      :image "Icons/Druid/ABILITY_DRUID_DEMORALIZINGROAR.png"}
     {:name "Swipe (Bear)"
      :image "Icons/Druid/INV_Misc_MonsterClaw_03.png"}
     {:name "Maul"
      :image "Icons/Druid/Ability_Druid_Maul.png"}
     {:name "Growl"
      :image "Icons/Druid/Ability_Physical_Taunt.png"}
     {:name "Bear Form"
      :image "Icons/Druid/Ability_Racial_BearForm.png"}]
    :restoration
    [{:name "Rejuvenation"
      :image "Icons/Druid/Spell_Nature_Rejuvenation.png"}
     {:name "Mark of the Wild"
      :image "Icons/Druid/Spell_Nature_Regeneration.png"}
     {:name "Healing Touch"
      :image "Icons/Druid/SPELL_NATURE_HEALINGTOUCH.png"}]}
   :hunter
   {:beast-mastery
    [{:name "Aspect of the Monkey"
      :image "Icons/Hunter/Ability_Hunter_AspectOfTheMonkey.png"}
     {:name "Aspect of the Hawk"
      :image "Icons/Hunter/Spell_Nature_RavenForm.png"}
     {:name "Tame Pet"
      :image "Icons/Hunter/Ability_Hunter_BeastTaming.png"}]
    :marksmanship
    [{:name "Hunter's Mark"
      :image "Icons/Hunter/Ability_Hunter_SniperShot.png"}
     {:name "Serpent Sting"
      :image "Icons/Hunter/Ability_Hunter_Quickshot.png"}
     {:name "Arcane Shot"
      :image "Icons/Hunter/Ability_ImpalingBolt.png"}
     {:name "Concussive Shot"
      :image "Icons/Hunter/Spell_Frost_Stun.png"}
     {:name "Auto Shot"
      :image "Icons/Hunter/INV_Weapon_Bow_02.png"}]
    :survival
    [{:name "Mongoose Bite"
      :image "Icons/Hunter/Ability_Hunter_SwiftStrike.png"}
     {:name "Raptor Strike"
      :image "Icons/Hunter/Ability_MeleeDamage.png"}
     {:name "Tracking Mastery"
      :image "Icons/Hunter/Ability_Tracking.png"}]}
   :mage
   {:arcane
    [{:name "Conjure Food"}
     {:name "Arcane Intellect"
      :image "Icons/Mage/Spell_Holy_MagicalSentry.png"}
     {:name "Conjure Water"}
     {:name "Arcane Missiles"
      :image "Icons/Mage/Spell_Nature_StarFall.png"}]
    :fire
    [{:name "Fireball"
      :image "Icons/Mage/Spell_Fire_FireBolt02.png"}
     {:name "Fireblast"
      :image "Icons/Mage/Spell_Fire_Fireball.png"}]
    :frost
    [{:name "Frost Bolt"
      :image "Icons/Mage/Spell_Frost_FrostBolt02.png"}
     {:name "Frost Armor"
      :image "Icons/Mage/Spell_Frost_FrostArmor02.png"}]}
   :paladin
   {:holy
    [{:name "Holy Light"
      :image "Icons/Paladin/Spell_Holy_HolyBolt.png"}
     {:name "Blessing of Wisdom"
      :image "Icons/Paladin/Spell_Holy_SealOfWisdom.png"}
     {:name "Seal of Righteousness"
      :image "Icons/Paladin/Ability_ThunderBolt.png"}]
    :protection
    [{:name "Divine Protection"}
     {:name "Devotion Aura"
      :image "Icons/Paladin/SPELL_HOLY_DEVOTIONAURA.png"}
     {:name "Hammer of Justice"}
     {:name "Righteous Fury"
      :image "Icons/Paladin/Spell_Holy_SealOfFury.png"}]
    :retribution
    [{:name "Blessing of Might"
      :image "Icons/Paladin/Spell_Holy_FistOfJustice.png"}
     {:name "Judgement of Light"
      :image "Icons/Paladin/Spell_Holy_RighteousFury.png"}
     {:name "Judgement of Wisdom"
      :image "Icons/Paladin/Ability_Paladin_JudgementBlue.png"}]}
   :priest
   {:discipline
    [{:name "Power Word: Shield"}
     {:name "Holy Word: Fortitude"
      :image "Icons/Priest/Spell_Holy_WordFortitude.png"}]
    :holy
    [{:name "Renew"
      :image "Icons/Priest/Spell_Holy_Renew.png"}
     {:name "Smite"
      :image "Icons/Priest/Spell_Holy_HolySmite.png"}
     {:name "Heal"
      :image "Icons/Priest/Spell_Holy_GreaterHeal.png"}]
    :shadow
    [{:name "Fade"}
     {:name "Shadow World: Pain"
      :image "Icons/Priest/Spell_Shadow_ShadowWordPain.png"}]}
   :rogue
   {:assassination
    [{:name "Eviscerate"}
     {:name "Slice and Dice"
      :image "Icons/Rogue/Ability_Rogue_SliceDice.png"}]
    :combat
    [{:name "Backstab"
      :image "Icons/Rogue/Ability_BackStab.png"}
     {:name "Sinister Strike"
      :image "Icons/Rogue/Spell_Shadow_RitualOfSacrifice.png"}
     {:name "Gouge"
      :image "Icons/Rogue/Ability_Gouge.png"}
     {:name "Sprint"
      :image "Icons/Rogue/Ability_Rogue_Sprint.png"}
     {:name "Evasion"
      :image "Icons/Rogue/Spell_Shadow_ShadowWard.png"}]
    :subtlety
    [{:name "Pick Pocket"}
     {:name "Sealth"
      :image "Icons/Rogue/Ability_Stealth.png"}]}
   :shaman
   {:elemental
    [{:name "Lightning Bolt"
      :image "Icons/Shaman/Spell_Nature_Lightning.png"}
     {:name "Earthbind Totem"}
     {:name "Searing Totem"
      :image "Icons/Shaman/Spell_Fire_SearingTotem.png"}
     {:name "Stoneclaw Totem"
      :image "Icons/Shaman/Spell_Nature_StoneClawTotem.png"}
     {:name "Earth Shock"
      :image "Icons/Shaman/Spell_Nature_EarthShock.png"}]
    :enhancement
    [{:name "Lightning Shield"
      :image "Icons/Shaman/Spell_Nature_LightningShield.png"}
     {:name "Stoneskin Totem"
      :image "Icons/Shaman/Spell_Nature_StoneSkinTotem.png"}]
    :restoration
    [{:name "Healing Wave"
      :image "Icons/Shaman/Spell_Nature_MagicImmunity.png"}]}
   :warlock
   {:affliction
    [{:name "Corruption"
      :image "Icons/Warlock/Spell_Shadow_AbominationExplosion.png"}
     {:name "Curse of Weakness"
      :image "Icons/Warlock/Spell_Shadow_CurseOfMannoroth.png"}
     {:name "Curse of Agony"
      :image "Icons/Warlock/Spell_Shadow_CurseOfSargeras.png"}
     {:name "Drain Soul"}
     {:name "Life Tap"
      :image "Icons/Warlock/Spell_Shadow_BurningSpirit.png"}
     {:name "Fear"}]
    :demonology
    [{:name "Demon Skin"
      :image "Icons/Warlock/Spell_Shadow_RagingScream.png"}
     {:name "Summon Imp"
      :image "Icons/Warlock/Spell_Shadow_SummonImp.png"}
     {:name "Summon Voidwalker"}]
    :destruction
    [{:name "Immolate"
      :image "Icons/Warlock/Spell_Fire_Immolation.png"}
     {:name "Shadow Bolt"
      :image "Icons/Warlock/Spell_Shadow_ShadowBolt.png"}]}
   :warrior
   {:arms
    [{:name "Heroic Strike"
      :image "Icons/Warrior/Ability_Rogue_Ambush.png"}
     {:name "Charge"
      :image "Icons/Warrior/Ability_Warrior_Charge.png"}
     {:name "Rend"
      :image "Icons/Warrior/Ability_Gouge.png"}
     {:name "Hamstring"
      :image "Icons/Warrior/Ability_ShockWave.png"}
     {:name "Battle Stance"
      :image "Icons/Warrior/Ability_Warrior_OffensiveStance.png"}
     {:name "Thunderclap"
      :image "Icons/Warrior/SPELL_NATURE_THUNDERCLAP.png"}
     {:name "Overpower"
      :image "Icons/Warrior/Ability_MeleeDamage.png"}]
    :fury
    [{:name "Battle Shout"
      :image "Icons/Warrior/Ability_Warrior_BattleShout.png"}
     {:name "Victory Rush"
      :image "Icons/Warrior/Ability_Warrior_Devastate.png"}]
    :protection
    [{:name "Defensive Stance"
      :image "Icons/Warrior/Ability_Warrior_DefensiveStance.png"}
     {:name "Shield Bash"
      :image "Icons/Warrior/Ability_Warrior_ShieldBash.png"}
     {:name "Taunt"
      :image "Icons/Warrior/Spell_Nature_Reincarnation.png"}
     {:name "Shield Block"
      :image "Icons/Warrior/Ability_Defend.png"}
     {:name "Berserker Rage"
      :image "Icons/Warrior/Ability_Racial_BloodRage.png"}]}})

(defn spell-icon
  [spell on-change picked? pickable?]
  [box
   :child
   (if picked?
     [:img.picked.pickable
      {:src (:image spell)
       :on-click #(on-change spell (not picked?))}]
     (if pickable?
       [:img.pickable
        {:src (:image spell)
         :on-click #(on-change spell (not picked?))}]
       [:img.not-pickable
        {:src (:image spell)}]))])

(defn picker
  [on-change picked]
  (let [can-pick-more? (< (count picked) 4)]
    [v-box
     :class "ability-picker"
     :width "50%"
     :gap "2em"
     :children
     (for [[class specs] (sort all-spells)]
       ^{:key class}
       [h-box
        :gap "1em"
        :children
        (for [[spec spells] (sort specs)]
          [h-box
           :children
           (for [spell spells
                 :when (:image spell)]
             ^{:key (:id spell)}
             (let [spell (assoc spell :class class :spec spec)]
               [spell-icon
                spell
                on-change
                (contains? picked spell)
                can-pick-more?]))])])]))

(defn select-class-macro-text
  [class-index]
  (str "/click CA2CharacterAdvancementMainClassButton"
       (inc class-index)
       \newline))

(defn spell-macro-text
  [spell-index spec-index]
  (str
   "/click CA2.CharacterAdvancementMain.Main.Tree" (inc spec-index)
   ".Content.Spells.Button" (inc spell-index) \newline
   "/click DropDownList1Button3" \newline))

(defn ordered-spells
  [all-spells]
  (->> (sort all-spells)
       (map (fn [[class specs]]
              (map (fn [[spec spells]]
                     (map (fn [spell]
                            (assoc spell
                                   :class class
                                   :spec spec))
                          spells))
                   (sort specs))))))

(defn macro-text
  [picked]
  (apply
   str
   (for [[class-index class-spells] (map-indexed vector (ordered-spells all-spells))]
     (apply
      str
      (select-class-macro-text class-index)
      (for [[spec-index spells] (map-indexed vector class-spells)]
        (apply str (->> spells
                        (map-indexed vector)
                        (reduce (fn [xs [i spell]]
                                  (if (contains? picked spell)
                                    xs
                                    (conj xs i)))
                                [])
                        (map #(spell-macro-text % spec-index)))))))))

(defn macro-textarea
  [picked]
  [input-textarea
   :model (macro-text picked)
   :width "100%"
   :rows 10
   :on-change (fn [])
   :disabled? true])

(defn macro-generator []
  (let [picked (:picked @app-state)
        on-change (fn [spell picked?]
                    (if picked?
                      (swap! app-state update :picked conj spell)
                      (swap! app-state update :picked disj spell)))]
    [v-box
     :children
     [[picker on-change picked] [macro-textarea picked]]]))

(defn mount [el]
  (rdom/render [macro-generator] el))

(defn mount-app-element []
  (when-let [el (gdom/getElement "app")]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
