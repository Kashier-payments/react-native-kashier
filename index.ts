import { KashierConfig } from "./src/KashierConfig";
import { KashierInit } from "./src/KashierInit";
import { KashierServices } from "./src/KashierServices";
import { Card } from "./src/Model/Card";
import { ConnectedAccount } from "./src/Model/ConnectedAccounts";

const Kashier = {
  ...KashierConfig,
  ...KashierInit,
  ...KashierServices,
  Card,
  ConnectedAccount
};
export { Kashier };
