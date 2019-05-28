package arven.doot.plugin;

import com.google.inject.Inject;
import flavor.pie.mcmoji.MCMoji;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.effect.sound.SoundCategories;
import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Plugin(id = "doot", name = "doot", version = "1.0.0",
        authors = {"doot"}, url = "https://ore.spongepowered.org/doot/doot",
        description = "doot doot", dependencies = @Dependency(id = "mcmoji", optional = true))
public class Doot {

    private final Logger logger;

    @Inject
    public Doot(Logger logger) {
        this.logger = logger;
    }

    @Listener
    public void onInit(GameInitializationEvent event) {
        logger.info("Registering commands... doot doot!");

        final Doot plugin = this;

        CommandSpec doot = CommandSpec.builder()
                .permission("doot.doot")
                .arguments(GenericArguments.allOf(GenericArguments.literal(Text.of("doot"), "doot")))
                .executor(((src, args) -> {
                    int doots = args.getAll("doot").size();

                    if (src instanceof ConsoleSource) {
                        Task.builder()
                                .execute(new Consumer<Task>() {
                                    private int current = 0;

                                    @Override
                                    public void accept(Task task) {
                                        if (current >= doots) {
                                            task.cancel();
                                        }
                                        current++;

                                        for (Player player : Sponge.getServer().getOnlinePlayers()) {
                                            player.playSound(SoundTypes.ENTITY_VILLAGER_TRADING,
                                                    player.getPosition(), 1, 2);
                                            player.sendMessage(Text.of(Doot.this.getDootFor(player.getUniqueId())));
                                        }
                                    }
                                })
                                .interval(300, TimeUnit.MILLISECONDS)
                                .submit(plugin);
                    } else if (src instanceof Player) {
                        Task.builder()
                                .execute(new Consumer<Task>() {
                                    private int current = 0;

                                    @Override
                                    public void accept(Task task) {
                                        if (current >= doots) {
                                            task.cancel();
                                        }
                                        current++;

                                        ((Player) src).playSound(SoundTypes.ENTITY_VILLAGER_TRADING,
                                                ((Player) src).getPosition(), 1, 2);
                                        src.sendMessage(Text.of(Doot.this.getDootFor(((Player) src).getUniqueId())));
                                    }
                                })
                                .interval(300, TimeUnit.MILLISECONDS)
                                .submit(plugin);
                    } else {
                        throw new CommandException(Text.of("You must be a player to use that command!"));
                    }

                    return CommandResult.success();
                }))
                .build();

        Sponge.getCommandManager().register(this, doot, "doot");
    }

    private String getDootFor(UUID id) {
        if (Sponge.getPluginManager().isLoaded("mcmoji")) {
            if (!MCMoji.Companion.getNoEmoji().contains(id)) {
                Character trumpet = MCMoji.Companion.getEmojiMap().get("trumpet");
                if (trumpet != null) {
                    return trumpet.toString();
                }
            }
        }
        return "doot!";
    }

}