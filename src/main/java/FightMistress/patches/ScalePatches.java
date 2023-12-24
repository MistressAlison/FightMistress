package FightMistress.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ScalePatches {
    @SpirePatch2(clz = AbstractCreature.class, method = SpirePatch.CLASS)
    public static class ScaleField {
        public static SpireField<Float> finalScale = new SpireField<>(() -> 1f);
    }

    @SpirePatch2(clz = AbstractCreature.class, method = "loadAnimation")
    public static class GetScale {
        @SpirePostfixPatch
        public static void plz(AbstractCreature __instance, float scale) {
            ScaleField.finalScale.set(__instance, 1f/scale);
        }
    }
}
