package FightMistress.patches;

import FightMistress.TheFightMistress;
import basemod.patches.com.megacrit.cardcrawl.screens.mainMenu.ColorTabBar.ColorTabBarFix;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import javassist.CtBehavior;

import static FightMistress.MainModfile.makeID;

public class ColorTabPatch {
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(makeID(TheFightMistress.class.getSimpleName()));

    @SpirePatch2(clz = ColorTabBarFix.Render.class, method = "Insert")
    public static class ChangeName {
        @SpireInsertPatch(locator = Locator.class, localvars = {"playerClass", "tabName"})
        public static void plz(AbstractPlayer.PlayerClass playerClass, @ByRef String[] tabName) {
            if (playerClass == TheFightMistress.Enums.THE_FIGHT_MISTRESS) {
                tabName[0] = characterStrings.NAMES[2];
            }
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher m = new Matcher.MethodCallMatcher(FontHelper.class, "renderFontCentered");
                return LineFinder.findInOrder(ctBehavior, m);
            }
        }
    }
}
