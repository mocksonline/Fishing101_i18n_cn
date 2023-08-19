package net.macck209.fishing101.items;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.sgui.api.elements.BookElementBuilder;
import eu.pb4.sgui.api.gui.BookGui;
import net.macck209.fishing101.polymer.PolymerTextures;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealBookItem extends Item implements PolymerItem {

    public MealBookItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {return PolymerTextures.MODELS.get(this).value();}

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable("Johnny的烹饪书");
    }
    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.WRITTEN_BOOK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof ServerPlayerEntity player) {
            new IndexGui(player, hand).open();
            user.useBook(user.getStackInHand(hand), hand);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return TypedActionResult.success(user.getStackInHand(hand), true);
        }

        return super.use(world, user, hand);
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt()) {
            NbtCompound nbtCompound = stack.getNbt();
            String string = "Johnny Gourmet";
            if (!StringHelper.isEmpty(string)) {
                tooltip.add(Text.translatable("book.byAuthor", string).formatted(Formatting.GRAY));
            }

            assert nbtCompound != null;
            tooltip.add(Text.translatable("book.generation." + nbtCompound.getInt("generation")).formatted(Formatting.GRAY));
        }

    }


    public static void build(){
        var builder = new BookElementBuilder();
        FishGui.BOOKS.clear();

        builder.addPage(
        Text.literal(" 介绍\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("欢迎来到我的烹饪之旅！加入我一起揭开精致味道的秘密，深入烹饪艺术的世界。让我们一起创造美味的杰作。祝您用餐愉快!")
        );
        builder.addPage(
        Text.literal("骨粉\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("配方\n").formatted(Formatting.UNDERLINE),
        Text.literal("由鱼骨制成")
        );
        builder.addPage(
        Text.literal("粘液球\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("配方\n").formatted(Formatting.UNDERLINE),
        Text.literal("- 浓稠的黏液\n- 绿色染料")
        );
        builder.addPage(
        Text.literal("面团\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("配方\n").formatted(Formatting.UNDERLINE),
        Text.literal("- 小麦\n- 鸡蛋")
        );
        builder.addPage(
        Text.literal("面条\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("配方\n").formatted(Formatting.UNDERLINE),
        Text.literal("在熔炉中烹饪面团")
        );
        builder.addPage(
        Text.literal("米饭\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("获取方式\n").formatted(Formatting.UNDERLINE),
        Text.literal("在樱花林的草丛中发现")
        );
        builder.addPage(
        Text.literal("鱿鱼须").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("获取方式\n").formatted(Formatting.UNDERLINE),
        Text.literal("砍鱿鱼")
        );
        builder.addPage(
        Text.literal("熟鱿鱼须").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("配方\n").formatted(Formatting.UNDERLINE),
        Text.literal("在熔炉中烹饪鱿鱼须")
        );
        builder.addPage(
        Text.literal("鸡汤\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("配方\n").formatted(Formatting.UNDERLINE),
        Text.literal("- 碗\n- 面条\n- 生鸡肉")
        );
        builder.addPage(
        Text.literal("紫色意面\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("配方\n").formatted(Formatting.UNDERLINE),
        Text.literal("- 碗\n- 面条\n- 熟猪排\n- 紫颂果")
        );
        builder.addPage(
        Text.literal("海鲜意面\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("配方\n").formatted(Formatting.UNDERLINE),
        Text.literal("- 碗\n- 面条\n- 熟鱿鱼须\n- 任何熏制的虾")
        );
        builder.addPage(
        Text.literal("海星烤肉串\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("配方\n").formatted(Formatting.UNDERLINE),
        Text.literal("- 海星\n- 木棍")
        );
        builder.addPage(
        Text.literal("末地海星烤肉串\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("配方\n").formatted(Formatting.UNDERLINE),
        Text.literal("- 末地海星\n- 木棍")
        );
        builder.addPage(
        Text.literal("寿司\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("配方\n").formatted(Formatting.UNDERLINE),
        Text.literal("- 米饭\n- 海带\n- 生鲑鱼")
        );
        builder.addPage(
        Text.literal("浓稠的黏液\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("配方\n").formatted(Formatting.UNDERLINE),
        Text.literal("在熔炉或篝火中烹饪水母或末地水母")
        );
        builder.addPage(
        Text.literal("充能雷鳍鱼\n\n").formatted(Formatting.BOLD,Formatting.GOLD),
        Text.literal("配方\n").formatted(Formatting.UNDERLINE),
        Text.literal("在锻造台中用2个铜块给雷鳍鱼充能")
        );

        IndexGui.book = builder.asStack();
    }


    public static final class IndexGui extends BookGui {
        public static ItemStack book;
        private final ItemStack stack;
        private final Hand hand;

        public IndexGui(ServerPlayerEntity player, Hand hand) {
            super(player, book);
            this.stack = player.getStackInHand(hand);
            this.hand = hand;
            this.setPage(Math.min(stack.getOrCreateNbt().getInt("Page"), book.getNbt().getList("pages", NbtElement.STRING_TYPE).size() - 1));
        }

        @Override
        public void onOpen() {
            super.onOpen();
        }

        @Override
        public void onClose() {
            super.onClose();
        }

        @Override
        public void onTakeBookButton() {
            this.close();
        }

        @Override
        public void setPage(int page) {
            super.setPage(page);
            this.player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.BLOCKS, 1f, 1);

            if (this.stack == this.player.getStackInHand(hand)) {
                this.stack.getOrCreateNbt().putInt("Page", page);
            }
        }
    }
    public static final class FishGui extends BookGui {
        public static final Map<Identifier, ItemStack> BOOKS = new HashMap<>();
        private final Runnable runnable;
        private boolean forceReopen;

        public FishGui(ServerPlayerEntity player, Identifier identifier, boolean forceReopen, Runnable runnable) {
            super(player, BOOKS.get(identifier));
            this.runnable = runnable;
            this.forceReopen = forceReopen;
        }

        @Override
        public void setPage(int page) {
            super.setPage(page);
            this.player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.BLOCKS, 1f, 1);
        }

        @Override
        public void onTakeBookButton() {
            super.onTakeBookButton();
            this.close();
        }

        @Override
        public void onClose() {
            if (this.forceReopen) {
                this.open();
                this.forceReopen = false;
            } else {
                super.onClose();
                runnable.run();
            }
        }
    }
}
