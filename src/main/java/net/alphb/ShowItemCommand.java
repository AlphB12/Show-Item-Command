package net.alphb;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import static net.minecraft.server.command.CommandManager.literal;


public final class ShowItemCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("showitem").executes(ctx -> broadcast(ctx.getSource())));
    }

    public static int broadcast(ServerCommandSource source) throws CommandSyntaxException {
        final @NotNull ServerPlayerEntity player = source.getPlayerOrThrow();
        Text text = Text.translatable(
                "showItem",
                player.getDisplayName(),
                player.getMainHandStack().toHoverableText());
        source.getServer().getPlayerManager().broadcast(text, false);
        return Command.SINGLE_SUCCESS;
    }
}