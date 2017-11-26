/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security_tp2.protocol;

import security_tp2.utils.Stats;

public interface Protocol {
    Stats getStatsForProtocol(int puzzleLenght);
}
