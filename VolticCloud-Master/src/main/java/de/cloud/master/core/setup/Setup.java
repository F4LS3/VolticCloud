package de.cloud.master.core.setup;

import de.cloud.master.core.GroupMode;
import de.cloud.master.core.ServerGroup;
import de.cloud.master.core.Wrapper;
import de.cloud.master.manager.GroupManager;
import de.cloud.master.manager.WrapperManager;
import de.cloud.master.netty.handler.NetworkStringHandler;
import de.cloud.master.utils.logger;
import de.cloud.master.utils.loglevel;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;

public class Setup {

    public static void enterServerGroupSetup(String groupName) {
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(new DataInputStream(System.in)));
            ServerGroup group = new ServerGroup();
            group.setGroupName(groupName);
            int ram = 256;
            // WrapperManager.getWrapperManager().getAvailableWrappers();
            Wrapper wrapper;
            int maxPlayers = 1;
            GroupMode groupMode;

            new logger(loglevel.INFO, "Bitte gebe den RAM der Servergruppe in MB an!");
            ram = Integer.valueOf(r.readLine());

            new logger(loglevel.INFO, "Bitte gebe den Wrapper an, auf dem die Servergruppe sein soll!");
            new logger(loglevel.BLANK, "Verfügbare Wrapper: "+WrapperManager.getWrapperManager().getAvailableWrappers());
            wrapper = WrapperManager.getWrapperManager().getWrapper(r.readLine());

            new logger(loglevel.INFO, "Bitte gebe die Maximalen Spieler der Grupppe an!");
            maxPlayers = Integer.valueOf(r.readLine());

            new logger(loglevel.INFO, "Bitte gebe den GruppenModus der Gruppe an!");
            new logger(loglevel.INFO, "Verfügbare Modi: STATIC, DYNAMIC, GAMESERVER, LOBBY");
            groupMode = GroupMode.valueOf(r.readLine());

            group.setRam(ram);
            group.setWrapper(null);
            group.setMaxPlayers(maxPlayers);
            group.setGroupMode(groupMode);


            NetworkStringHandler.getChannel().writeAndFlush(
                    "create servergroup "+groupName+" "+ram+" "+wrapper.getWrapperName()+" "+maxPlayers+" "+groupMode.toString());

            GroupManager.getGroupManager().addAvailableServerGroup(group);
            new logger(loglevel.INFO, "Die ServerGruppe '"+groupName+"' wurde erfolgreich hinzugefügt!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
