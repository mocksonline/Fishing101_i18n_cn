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

public class FishBookItem extends Item implements PolymerItem {

    public FishBookItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {return PolymerTextures.MODELS.get(this).value();}

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable("Ichthyologist's Journal");
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
            String string = "Dr. Finn Benthos";
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
    Text.literal("   十月12日，1878年\n"),
    Text.literal("我的名字是Finn Benthos。本日志的页面将记录我在未知航程中遇到的有趣海洋生物。我的目标是揭开水域中隐藏的神秘。")
);
builder.addPage(
    Text.literal("   十月13日，1878年\n\n"),
    Text.literal("鲤鱼\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鲤形目（Cypriniformes）\n" +
        "- 分布：淡水\n" +
        "- 可以烟熏")
);
builder.addPage(
    Text.literal("   十月13日，1878年\n\n"),
    Text.literal("畸形鲤鱼\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鲤形目（Cypriniformes）\n" +
        "- 分布：淡水\n" +
        "- 可以烟熏")
);
builder.addPage(
    Text.literal("   十月14日，1878年\n\n"),
    Text.literal("发光鲤鱼\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鲤形目（Cypriniformes）\n" +
        "- 分布：淡水；黄昏后\n" +
        "- 可以烟熏\n" +
        "- 特殊效果：发光")
);
builder.addPage(
    Text.literal("   十月14日，1878年\n\n"),
    Text.literal("鲶鱼\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鲶形目（Siluriformes）\n" +
        "- 分布：淡水\n" +
        "- 可以烟熏")
);
builder.addPage(
    Text.literal("   十月16日，1878年\n\n"),
    Text.literal("泥鲶鱼\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鲶形目（Siluriformes）\n" +
        "- 分布：混浊的沼泽水域\n" +
        "- 可以烟熏")
);
builder.addPage(
    Text.literal("   十月24日，1878年\n\n"),
    Text.literal("热带鲶鱼\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鲶形目（Siluriformes）\n" +
        "- 分布：热带水域\n" +
        "- 可以烟熏")
);
builder.addPage(
    Text.literal("   十一月8日，1878年\n\n"),
    Text.literal("神圣鲶鱼\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鲶形目（Siluriformes）\n" +
        "- 分布：淡水；罕见，但下雨时更常见\n" +
        "- 可以烟熏\n" +
        "- 特殊效果：升天")
);
builder.addPage(
    Text.literal("   十一月22日，1878年\n\n"),
    Text.literal("鲭鱼\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鲈形目（Perciformes）\n" +
        "- 分布：海水\n" +
        "- 可以烟熏")
);
builder.addPage(
    Text.literal("   十一月27日，1878年\n\n"),
    Text.literal("发光鲭鱼\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鲈形目（Perciformes）\n" +
        "- 分布：海水；黄昏后\n" +
        "- 可以烟熏\n" +
        "- 特殊效果：发光")
);
builder.addPage(
    Text.literal("   十二月1日，1878年\n\n"),
    Text.literal("红树梅鱼\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鲈形目（Perciformes）\n" +
        "- 分布：红树林水域\n" +
        "- 可以烟熏")
);
        builder.addPage(
    Text.literal("   十二月7日，1878年\n\n"),
    Text.literal("虾\n").formatted(Formatting.BOLD),
    Text.literal("- 目：十足目（Decapoda）\n" +
        "- 分布：海水\n" +
        "- 可以烟熏")
);
builder.addPage(
    Text.literal("   十二月14日，1878年\n\n"),
    Text.literal("发光虾\n").formatted(Formatting.BOLD),
    Text.literal("- 目：十足目（Decapoda）\n" +
        "- 分布：海水；黄昏后\n" +
        "- 可以烟熏\n" +
        "- 特殊效果：发光")
);
builder.addPage(
    Text.literal("   十二月15日，1878年\n\n"),
    Text.literal("水母\n").formatted(Formatting.BOLD),
    Text.literal("- 目：半口形目（Semaeostomeae）\n" +
        "- 分布：海水\n" +
        "- 可能引发过敏反应")
);
builder.addPage(
    Text.literal("   一月7日，1879年\n\n"),
    Text.literal("神圣水母\n").formatted(Formatting.BOLD),
    Text.literal("- 目：半口形目（Semaeostomeae）\n" +
        "- 分布：海水；罕见，但下雨时更常见\n" +
        "- 特殊效果：升天")
);
builder.addPage(
    Text.literal("   一月9日，1879年\n\n"),
    Text.literal("深海鮟鱇\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鮟鱇目（Lophiiformes）\n" +
        "- 分布：最深的洞穴中的绝对黑暗；在古代城市中更常见\n" +
        "- 可以烟熏\n" +
        "- 常引起恶心")
);
builder.addPage(
    Text.literal("   三月30日，1879年\n\n"),
    Text.literal("红色锦鲤\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鲤形目（Cypriniformes）\n" +
        "- 分布：樱花林\n" +
        "- 可以烟熏")
);
builder.addPage(
    Text.literal("   四月2日，1879年\n\n"),
    Text.literal("黄色锦鲤\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鲤形目（Cypriniformes）\n" +
        "- 分布：樱花林\n" +
        "- 可以烟熏")
);
builder.addPage(
    Text.literal("   六月19日，1879年\n\n"),
    Text.literal("雷鳍鱼\n").formatted(Formatting.BOLD),
    Text.literal("- 目：电鱼目（Fulmeniformes）\n" +
        "- 分布：带电水域\n" +
        "- 当与铜接触时，似乎具有一些异常属性")
);
builder.addPage(
    Text.literal("   七月25日，1879年\n\n"),
    Text.literal("海星\n").formatted(Formatting.BOLD),
    Text.literal("- 目：腕足目（Paxillosida）\n" +
        "- 分布：海水\n" +
        "- 适合制作沙什里克")
);
        
builder.addPage(
    Text.literal("   七月13日，1879年\n\n"),
    Text.literal("冰鳕鱼\n").formatted(Formatting.BOLD),
    Text.literal("- 目：鳕形目（Gadiformes）\n" +
        "- 分布：冰川下\n" +
        "- 真的非常冷"
    )
);
builder.addPage(
    Text.literal("   七月XX日，19XX年\n\n"),
    Text.literal("末地水母\n").formatted(Formatting.BOLD),
    Text.literal("- 目：半口形目???（Semaeostomeae）\n" +
        "- 分布：我在哪里？\n" +
        "- 可能引发过敏反应"
    )
);
builder.addPage(
    Text.literal("   七月XX日，19XX年\n\n"),
    Text.literal("末地海星\n").formatted(Formatting.BOLD),
    Text.literal("- 目：腕足目???（Paxillosida）\n" +
        "- 分布：我开始感到担心...\n" +
        "- 不知道它是否可食用，但我不会尝试"
    )
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
