//
//  KashierConnectedAccountParser.m
//  
//
//  Created by MacBook on 15/06/2021.
//

#import "KashierConnectedAccountParser.h"


@implementation  KashierConnectedAccountParser: NSObject

+ (Connected_Accounts*) parseConnectedAccountData: (NSDictionary*) connectedAccountData {
    Connected_Accounts* connectedAccount;
    NSString* mid = @"";
 
    
    if([KashierUtils isNonEmptyString:[connectedAccountData objectForKey:@"mid"]]  ){
        mid = [connectedAccountData objectForKey:@"mid"];
    }

    connectedAccount = [[Connected_Accounts alloc] initWithMid:mid];
    return connectedAccount;
}

@end
