import { Message } from "./Message";
import { User } from "./User";

export class Chat{
    id!: number;
    last_update!: Date;
    participants!: Array<User>;
    messages!: Array<Message>;
}